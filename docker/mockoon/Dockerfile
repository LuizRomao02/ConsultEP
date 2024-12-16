FROM mockoon/cli:latest

WORKDIR /mockoon

COPY api_mockada_cep.json /mockoon/
COPY mockoon_config.json /mockoon/

CMD ["-d", "api_mockada_cep.json", "--port", "3000"]
