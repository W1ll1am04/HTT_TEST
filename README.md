# HTT_TEST
Retrieve http status codes through console / terminal.

## Summary

  - [Getting Started](#getting-started)
  - [Executing](#Executing)
  - [Parameters](#Parameters)
  - [Authors](#Authors)
  - [License](#License)


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
See executing for notes on how to executing the project on a live system.

Get the jar from the repository releases page:
```
https://github.com/W1ll1am04/HTT_TEST/releases
```
## Executing

Run the jar:
```
java -jar htt_test.jar yoursite
```

## Parameters
```
Retrieve http status codes through console / terminal.
Arguments & usage, and an explanation:
Usage: HTT_TEST [URL] [URL] [-h] [-d item] [-s count] [-p protocol] [-g premission]
                [-S] [-H header] [-t count] [-r file]
 
-h, --help                           Display this message.
-d, --disable  [OPTION]              Disable a part of the default output.
-s, --sleepfor [COUNT]               Sleep for [count] secounds for every request.
-p, --protocol [PROTOCOL]            Add entered protcol to entered urls. Ex 'http://'
-g, --getpremission [PREMISSION]     Returns a SocketPermission representing the permission necessary to connect to the destination host and port.
-t, --times [COUNT]                  Run request(s) [COUNT] times.
-r, --readlist [FILE]                Read request url(s) from a simple file and loop thru them.
-S, --statusCodeOnly                 Output http status-code only.
-H, --header [HEADER]                Get specified request header(s).
```

## Authors

  **William E.I** - [W1ll1am04](https://github.com/w1ll1am04)

## License

This project is licensed under the GPL-3.0 license - see the [LICENSE](LICENSE) file for details
