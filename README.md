# chatroom-java-playframework-with-akka-actors-and-websockets
An open chat room using websockets and AKKA actors in play framework.


`$ ./activator clean stage`
`$ ./target ./target/universal/stage/bin/sockets  -Dconfig.file=/path/to/project/sockets/conf/application.conf -Dhttp.port=9001`


Visit `http://localhost:9001/ws` from differant tabs/windows/browsers and send message
