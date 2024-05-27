# Help

You don't need this to run the server, it has
been built and packaged.

## javascript

```bash
npm install
make dev # start development vite server
make build # build
make dist # copy over to the server (might not work)
```

## scala

```bash
sbt
sbt> ~fastLinkJS # compile scala ot js
sbt> ~fullLinkJS # dist scala to js (smaller footprint)
```

