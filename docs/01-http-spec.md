# http-spec

关于http协议的规范

apache httpclient对于http spec的模型还原度还是比较高的,可以作为学习http概念的切入口.
spring关于的http的抽象模型比较简陋.


## 编码
The default encoding for HTTP headers is ISO-8859-1 (also known as Latin-1).
This character encoding is specified by the HTTP/1.1 standard (RFC 2616) for both header names and values.
However, HTTP headers are usually limited to ASCII characters directly, so characters outside the ASCII range require encoding.

If non-ASCII characters need to be included in HTTP headers, they are often encoded using techniques such as percent encoding or Base64 encoding. 
This limitation applies only to headers; the body of an HTTP message can specify a different encoding (such as UTF-8) through the Content-Type header's charset parameter.

当提交的表单不包含文件时,可以使用application/x-www-form-urlencoded,且该请求必须是POST.
当提交的表单包含文件时,就必须使用multipart/form-data,且该请求必须是POST.

application/x-www-form-urlencoded会将non-ASCII characters使用percent-encoding转码
multipart/form-data不会对任何表单元素进行转码,保持原始的二进制数据进行传输.对于字符串就取其对应code-unit sequence;对于文件就取其原本的二进制数据.
