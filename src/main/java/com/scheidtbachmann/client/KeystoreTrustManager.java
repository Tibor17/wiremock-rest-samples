package com.scheidtbachmann.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

final class KeystoreTrustManager implements X509TrustManager {
    private static final Logger LOG = LoggerFactory.getLogger(KeystoreTrustManager.class);

    private final X509TrustManager pkixTrustManager;

    KeystoreTrustManager(File trustStore, char[] password) throws GeneralSecurityException, IOException {
        this(new FileInputStream(trustStore), password);

    }

    KeystoreTrustManager(final InputStream inputStream, final char[] password) throws GeneralSecurityException, IOException {
        try (final InputStream is = inputStream) {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(is, password);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("PKIX");
            tmf.init(ks);
            X509TrustManager pkixTrustManager = null;
            for (TrustManager tm : tmf.getTrustManagers()) {
                if (tm instanceof X509TrustManager) {
                    pkixTrustManager = (X509TrustManager) tm;
                    break;
                }
            }
            if (pkixTrustManager == null) {
                throw new IllegalStateException("Couldn't initialize");
            }
            this.pkixTrustManager = pkixTrustManager;
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        try {
            pkixTrustManager.checkClientTrusted(chain, authType);
        } catch (CertificateException e) {
            LOG.error(e.getLocalizedMessage(), e);
            throw new CertificateException(e);
        }
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        try {
            pkixTrustManager.checkServerTrusted(chain, authType);
        } catch (CertificateException e) {
            LOG.error(e.getLocalizedMessage(), e);
            throw new CertificateException(e);
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return pkixTrustManager.getAcceptedIssuers();
    }
}