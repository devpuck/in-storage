FROM hub.c.163.com/housan993/centos7_jdk8:8.0


MAINTAINER "xac"

## Set font
RUN mkdir /usr/local/font
RUN mkdir -p /usr/java/jdk1.8.0_131/jre/lib/fonts/fallback
WORKDIR /usr/local/font
RUN curl -O https://github.com/micmro/Stylify-Me/blob/master/.fonts/SimSun.ttf
RUN cp /usr/local/font/SimSun.ttf /usr/java/jdk1.8.0_131/jre/lib/fonts/fallback/

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime

RUN yum install -y wget && yum install -y tar  && yum install -y gzip  && yum clean all

RUN mkdir -p /home/admin/runtime && cd /home/admin/runtime && \
    wget "http://console.v190808.163skiff.com/download/nsf-agent-v2.5.6-1daf42fe-20190916-162559.jar" -O nsf-agent.jar

RUN mkdir -p /home/admin/runtime/apm && cd /home/admin/runtime/apm && \
    wget "http://console.v190808.163skiff.com/download/napm-java-agent-v5.3.3-190825-36860798.tar.gz" -O napm-java-agent.tar.gz  && \
    tar zxvf napm-java-agent.tar.gz &&\
    cd /home/admin/runtime/apm/napm-java-agent && \
    cd conf && \
    echo "endpoint=http://apm-collector.v190808.163skiff.com" >> napm-agent.properties && \
    echo "productId=177-xac" >> napm-agent.properties && \
    echo "service=xac-mes" >> napm-agent.properties

EXPOSE 8880

WORKDIR /
ADD ./*/target/xac-mes.jar /xac-mes.jar
CMD ["java","-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,address=5005,suspend=n","-javaagent:/home/admin/runtime/nsf-agent.jar=xac-mes","-javaagent:/home/admin/runtime/apm/napm-java-agent/napm-java-rewriter.jar=conf=napm-agent.properties","-jar","xac-mes.jar"]
