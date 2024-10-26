# apache httpclient4

apache httpclient对于http spec的模型还原度还是比较高的,可以作为学习http概念的切入口.
spring关于的http的模型比较简陋.

## 核心概念

### HttpRequest
http请求的抽象

```
Request = Request-Line
          *(( general-header
           | request-header
           | entity-header ) CRLF)
          CRLF
          [ message-body ]
```

### HttpResponse
http响应的抽象
```
Response = Status-Line
           *(( general-header
            | response-header
            | entity-header ) CRLF)
           CRLF
           [ message-body ]
```

### HttpMessage
http请求/响应的抽象
```
generic-message = start-line
                  *(message-header CRLF)
                  CRLF
                  [ message-body ]
start-line      = Request-Line | Status-Line
```
HttpMessage这个接口是对很久之前http协议的抽象,当前http协议已经发生了很大改变,该接口仅仅作为HttpRequest/HttpResponse java语法层面公共方法的复用.
不要过多关注该类.

### HttpEntity
An entity that can be sent or received with an HTTP message. 
Entities can be found in some requests and in responses, where they are optional.

There are three distinct types of entities in HttpCore, depending on where their content originates:
1. streamed: The content is received from a stream, or generated on the fly. In particular, this category includes entities being received from a connection. Streamed entities are generally not repeatable.
2. self-contained: The content is in memory or obtained by means that are independent from a connection or other entity. Self-contained entities are generally repeatable.
3. wrapping: The content is obtained from another entity.

被放在http的body中,被传送的数据.

#### multipart
AbstractMultipartForm(multipart真正抽象)
FormBodyPart(multipart中的part部分)
MultipartFormEntity是MultipartEntity的升级版,是对AbstractMultipartForm的薄薄抽象,提供了httpEntity的header和contentLength




### ClientExecChain

### MainClientExec
The last request executor in the HTTP request execution chain that is responsible for execution of request / response exchanges with the opposite endpoint. 
This executor will automatically retry the request in case of an authentication challenge by an intermediate proxy or by the target server.

发送用户url的请求,并获取响应报文,还能自动执行认证.
MainClientExec比HttpRequestExecutor更高层的抽象.HttpRequestExecutor更多关注io和协议的解析;MainClientExec关注的是协议层上的用户层面的概念.


### HttpRequestExecutor
最终执行blocking io操作的类,用来向socket写入/读取数据,以及协议的解析.

HttpRequestExecutor is a client side HTTP protocol handler based on the blocking (classic) I/O model.
HttpRequestExecutor relies on HttpProcessor to generate mandatory protocol headers for all outgoing messages and apply common, cross-cutting message transformations to all incoming and outgoing messages. 
Application specific processing can be implemented outside HttpRequestExecutor once the request has been executed and a response has been received.


#### DefaultHttpClientConnectionOperator

关注方法connect.
首先,ConnectionSocketFactory.createSocket创建的是一个初始的Socket对象(Creates new, unconnected socket. The socket should subsequently be passed to connectSocket method.)
然后,对初始的Socket对象进行httpclient自己的设置
然后,ConnectionSocketFactory.connectSocket开始建立socket连接(Connects the socket to the target host with the given resolved remote address.)
最后,为httpclient的ManagedHttpClientConnection的属性设置已经建立连接的Socket对象.


#### ConnectionSocketFactory
ConnectionSocketFactory的2个主要实现类:PlainConnectionSocketFactory/SSLConnectionSocketFactory

##### PlainConnectionSocketFactory

PlainConnectionSocketFactory.createSocket
创建初始的普通的Socket对象(new Socket())

PlainConnectionSocketFactory.connectSocket
Socket.bind(localAddress);
Socket.connect(remoteAddress, connectTimeout);


##### SSLConnectionSocketFactory
SSLConnectionSocketFactory通过一下方法,获取到jdk自带的SSLSocketFactory
javax.net.ssl.SSLContext.getDefault
javax.net.ssl.SSLContext.getSocketFactory
使用jdk自带的SSLSocketFactory创建初始的SSLSocket.

SSLConnectionSocketFactory.createSocket
创建的是普通的Socket对象,而非SSLSocket对象.

SSLConnectionSocketFactory.connectSocket
会将普通的Socket对象转换为SSLSocket对象,然后设置加密配置,然后建立socket连接(bind/connect),然后完成tcp握手过程(SSLSocket.startHandshake),将SSLSocket对象以返参给调用方.
如果接收到的是SSLSocket对象,然后建立socket连接,然后完成tcp握手过程,将入参对象以返参给调用方.



org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect


在解析header时,将byte[]转换为CharArrayBuffer时,仅仅是简单的cast
The bytes are converted to chars using simple cast.类似于下面的cast强转.
this.buffer[i2] = (char) (b[i1] & 0xff);
因为http的header都是简单的iso8859-1字符,一个char可以hold住