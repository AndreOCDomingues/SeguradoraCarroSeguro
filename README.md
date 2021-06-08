# SeguradoraCarroSeguro

Projeto CRUD para cadastro de clientes e apolices utilizando Java 8, Spring boot e MongoDB.
Também foi incluído uma consulta de apolice separada dos CRUDs, onde constam algumas informações mais detalhadas referente a apolice.
Ex.: se ela já está vencida ou não, a quantos dias está vencida (caso esteja) ou quantos dias falta para vencer (caso não esteja).

Foi documentado os endpoints utilizando o postman.
O collection do postman está "./postmanCollection/SeguradoraCarroSeguro.postman_collection.json"

O jar para execução está em "./SeguradoraCarroSeguro/target/SeguradoraCarroSeguro-0.0.1-SNAPSHOT.jar" assim como o start.bat para inicia-lo.

Para implementação utilizei o MongoDB Atlas.
DatabaseName: scsdb
Collections: apolice, cliente
