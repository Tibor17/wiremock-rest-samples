# Readme

### Infrastructure Setup

Take care!

There is `username:password` in URL which is dummy and unknown for me!

I do not know real one.
Therefore my CURL command has the parameter `--insecure` and SSL was ignored in my hands on tests.

The SSL here needs a proper configuration setup.

```
/subsystem=naming/binding=java\:global\/jrs\/connection\/rest\/root/:add(binding-type=simple, type=java.net.URI, cache=false, value="https://username:password@10.46.26.20:8443/reportws/rest/reportExecutions")
```

### Practicle Experience with JRS - CURL
Invoke the request on real JRS Adapter.

Downloads PDF in current dir:
```
curl --insecure -H "Content-Type: application/xml" -H "Accept-Language: de" -w "time_total: %{time_total}" -d @D:\projects\jasper-invoice\src\main\scripts\invoice-report5.xml -o inv.pdf -X POST https://10.46.26.20:8443/reportws/rest/reportExecutions/tenants/default/reports/invoicing/main/001_invoice?outputFormat=pdf
```

alternatively CSV:
```
curl --insecure -H "Content-Type: application/xml" -H "Accept-Language: de" -w "time_total: %{time_total}" -d @D:\projects\jasper-invoice\src\main\scripts\invoice-report5.xml -X POST https://10.46.26.20:8443/reportws/rest/reportExecutions/tenants/default/reports/invoicing/main/001_invoice?outputFormat=csv
```

### References

[Example with JAX-RS 2.0 RESTEasy Client](https://howtodoinjava.com/resteasy/jax-rs-2-0-resteasy-3-0-2-final-client-api-example/)


