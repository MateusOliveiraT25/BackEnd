<?php
require_once 'conectaBD.php';

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

    echo "Tabela 'anuncio' criada com sucesso!";
} catch (PDOException $e) {
    die("Erro ao criar tabela 'anuncio': " . $e->getMessage());
}

// Inserção de dados na tabela 'anuncio'
session_start();
if (empty($_SESSION)) {
    header("Location: index.php?msgErro=Você precisa se autenticar no sistema.");
    die();
}

if (!empty($_POST)) {
    if ($_POST['enviarDados'] == 'CAD') {
        try {
            $sqlInsert = "INSERT INTO anuncio
                (fase, tipo, porte, sexo, pelagem_cor, raca, observacao, email_usuario)
                VALUES
                (:fase, :tipo, :porte, :sexo, :pelagem_cor, :raca, :observacao, :email_usuario)";

            $stmtInsert = $pdo->prepare($sqlInsert);
            $dados = array(
                ':fase' => $_POST['fase'],
                ':tipo' => $_POST['tipo'],
                ':porte' => $_POST['porte'],
                ':sexo' => $_POST['sexo'],
                ':pelagem_cor' => $_POST['pelagemCor'],
                ':raca' => $_POST['raca'],
                ':observacao' => $_POST['observacao'],
                ':email_usuario' => $_SESSION['email']
            );

            if ($stmtInsert->execute($dados)) {
                header("Location: index_logado.php?msgSucesso=Anúncio cadastrado com sucesso!");
            }
        } catch (PDOException $e) {
            header("Location: index_logado.php?msgErro=Falha ao cadastrar anúncio.");
        }
    } else {
        header("Location: index_logado.php?msgErro=Erro de acesso (Operação não definida).");
    }
} else {
    header("Location: index_logado.php?msgErro=Erro de acesso.");
}

// Redirecionar para a página inicial (index_logado) com mensagem de erro/sucesso
die();
?>
