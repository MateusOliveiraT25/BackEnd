<?php
// endereco
// nome do BD
// admin
// senha
$endereco = 'localhost';
$banco = 'mateusoliveira';
$admin = 'postgres';
$senha = 'postgres';
try {
// sgbd:host;port;dbname
// admin
// senha
// errmode
$pdo = new PDO("pgsql:host=$endereco;port=5432;dbname=$banco", $admin, $senha,
[PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]);

$sql = "CREATE TABLE IF NOT EXISTS usuarios (
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL
)";

// Executar a consulta SQL
$pdo->exec($sql);

} catch (PDOException $e) {
echo "Falha ao conectar ao banco de dados. <br/>";
die($e->getMessage());
}

// Criação da tabela 'anuncio' se ela não existir
try {
    $sqlCreateTable = "CREATE TABLE IF NOT EXISTS anuncio (
        id SERIAL PRIMARY KEY,
        fase VARCHAR(255),
        tipo VARCHAR(255),
        porte VARCHAR(255),
        sexo VARCHAR(255),
        pelagem_cor VARCHAR(255),
        raca VARCHAR(255),
        observacao TEXT,
        email_usuario VARCHAR(255)
    )";

    $stmtCreateTable = $pdo->prepare($sqlCreateTable);
    $stmtCreateTable->execute();

  
} catch (PDOException $e) {
    die("Erro ao criar tabela 'anuncio': " . $e->getMessage());
}

?>