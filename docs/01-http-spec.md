# http-spec

关于http协议的规范

apache httpclient对于http spec的模型还原度还是比较高的,可以作为学习http概念的切入口.
spring关于的http的抽象模型比较简陋.


## 编码
The default encoding for HTTP headers is ISO-8859-1 (also known as Latin-1).
This character encoding is specified by the HTTP/1.1 standard (RFC 2616) for both header names and values.
However, HTTP headers are usually limited to ASCII characters directly, so characters outside the ASCII range require encoding.

If non-ASCII characters need to be included in HTTP headers, they are often encoded using techniques such as percent encoding or Base64 encoding. This limitation applies only to headers; the body of an HTTP message can specify a different encoding (such as UTF-8) through the Content-Type header's charset parameter.