package com.scheidtbachmann.client;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

final class KeystoreManager implements X509KeyManager {
    private final X509KeyManager pkixKeyManager;

    KeystoreManager(File keyStore, char[] password) throws GeneralSecurityException, IOException {
        this(new FileInputStream(keyStore), password);
    }

    KeystoreManager(final InputStream inputStream, final char[] password) throws GeneralSecurityException, IOException {
        try (final InputStream is = inputStream) {
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(is, password);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
            kmf.init(ks, password);
            X509KeyManager pkixKeyManager = null;
            for (KeyManager km : kmf.getKeyManagers()) {
                if (km instanceof X509KeyManager) {
                    pkixKeyManager = (X509KeyManager) km;
                    break;
                }
            }
            if (pkixKeyManager == null) {
                throw new IllegalStateException("Couldn't initialize");
            }
            this.pkixKeyManager = pkixKeyManager;
        }
    }

    public PrivateKey getPrivateKey(String arg0) {
        return pkixKeyManager.getPrivateKey(arg0);
    }

    public X509Certificate[] getCertificateChain(String arg0) {
        return pkixKeyManager.getCertificateChain(arg0);
    }

    public String[] getClientAliases(String arg0, Principal[] arg1) {
        return pkixKeyManager.getClientAliases(arg0, arg1);
    }

    public String chooseClientAlias(String[] arg0, Principal[] arg1, Socket arg2) {
        return pkixKeyManager.chooseClientAlias(arg0, arg1, arg2);
    }

    public String[] getServerAliases(String arg0, Principal[] arg1) {
        return pkixKeyManager.getServerAliases(arg0, arg1);
    }

    public String chooseServerAlias(String arg0, Principal[] arg1, Socket arg2) {
        return pkixKeyManager.chooseServerAlias(arg0, arg1, arg2);
    }

}