
Shortening Service 
=======================


1.Build
---------

    $ mvn package -DskipTests
    
  - mvn package 명령어를 통해서 ./target/musinsa-0.1.jar 파일을 만들어 줍니다.
  - musinsa-0.1.jar 파일을 linux 서버 폴더로 upload 합니다.   

1.1 linux java 설치
------------------
linux 서버에 java가 설치되어 있는 않은경우 java 설치가 필요합니다.
java 설치가 되어 있다면 바로 2 단계로 가시면 됩니다.

    $ apt-get update
    $ apt-get install openjdk-8-jdk
    $ java -version
    
2.Start & Test
------------------
linux 서버의 musinsa-0.1.jar 파일이 있는 directory 로 이동합니다.    
   
    $ java -jar musinsa-0.1.jar
    
    
- *localhost/static/urlForm.html* :URL 입력 폼 및 결과 확인 화면 입니다.
- 변환 하고자 하는 URL 을 input 박스에 입력하고 '변환' 버튼을 클릭합니다.
- 짧게 변환된 URL 결과가 화면에 노출됩니다.
- 짧게 변환된 URL 결과를 클릭시 입력한 URL 화면으로 접속됩니다. 

