FROM postgres:17-alpine

# set timezone
ENV TZ='Europe/Stockholm'
ENV POSTGRES_ROOT_PASSWORD=root
ENV POSTGRES_DB=hd
ENV POSTGRES_USER=hd
ENV POSTGRES_PASSWORD=hd

# upgrade system and install essential tools
#RUN apt-get update && apt-get upgrade -y

ADD initdb.d /docker-entrypoint-initdb.d/
#ADD seeds /docker-entrypoint-initdb.d/

ENTRYPOINT ["docker-entrypoint.sh"]

CMD ["postgres"]