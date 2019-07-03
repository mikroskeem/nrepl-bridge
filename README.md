# nrepl-bridge

[![Build Status](https://travis-ci.org/mikroskeem/nrepl-bridge.svg?branch=master)](https://travis-ci.org/mikroskeem/nrepl-bridge)

Java library to control [nREPL](https://github.com/nrepl/nrepl) launch from Java code

## Why?

Because

```java
NreplBridge bridge = new NreplBridge();
bridge.bootServer("127.0.0.1", 1337);
```

is way better than invoking Clojure code directly to achieve the same.

### What about custom handlers?

Don't worry, I got you covered. You can pass handler function reference or symbol name as a string
(it'll get dereferenced via `(symbol <handler>)`)

```java
bridge.bootServerWithHandler("my.namespace/cool-nrepl-handler", "127.0.0.1", 1337);
```

### Help! Cider does not work!

[I know](https://github.com/clojure-emacs/cider-nrepl/issues/447), here's a workaround:

```java
String ciderBoot = "(ns user-cider-fix)" +
        "(defn cider-nrepl-handler []" +
        " (require 'cider.nrepl)" +
        " (ns-resolve 'cider.nrepl 'cider-nrepl-handler))";
Compiler.load(new StringReader(ciderBoot));
Object ciderHandler = RT.var("user-cider-fix", "cider-nrepl-handler").invoke();
bridge.bootServerWithHandler(ciderHandler, "127.0.0.1", 1337);
```

Best I could come up with right now, sorry. Ideally this should be provided as an utility
function (TODO)

## License

Copyright © 2019 Mark Vainomaa

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
