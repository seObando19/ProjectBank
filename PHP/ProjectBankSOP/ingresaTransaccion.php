<?php 

if( isset($_REQUEST['nrocuentaorigen']) && isset($_REQUEST['nrocuentadestino']) &&
isset($_REQUEST['valor'])) {

$nrocuentaorigen=$_POST['nrocuentaorigen'];
$nrocuentadestino=$_POST['nrocuentadestino'];
$valor=$_POST['valor'];
$cnx =  mysqli_connect("localhost","root","","bd_projectbank") or die("Ha sucedido un error inexperado en la conexion de la base de datos");

//Saldo actualde cuenta origen y destino
$resultOrigen = mysqli_query($cnx,"select saldo from cuenta where nrocuentaorigen = '$nrocuentaorigen'");
$resultDestino = mysqli_query($cnx,"select saldo from cuenta where nrocuentaorigen = '$nrocuentaorigen'");

//Validar i la consulta si trae data
if ($resultOrigen->num_rows && $resultDestino->num_rows)
{
	$row = $resultOrigen->fetch_object();
	$rowDestino = $resultDestino->fetch_object();
	$saldoOrigen = $row->saldo;
	$saldoDestino = $rowDestino->saldo;
}

//Validacion del saldo actual y actulizacion de la cuenta origen y destino
if ($valor < $saldoOrigen) {
	$updateSaldoOrigen = $saldoOrigen -$valor;
	$updateSaldoDestino = $saldoDestino + $valor;
}else {
	echo "EL VALOR EXCEDE EL SALDO ORIGEN DE LA CUENTA";
}

//Actualizacion de datos en las tablas

//actualizar cuenta origen
mysqli_query($cnx,"UPDATE cuenta SET saldo = $updateSaldoOrigen WHERE nrocuenta = '$nrocuentaorigen'");

//actualizar cuenta destino
mysqli_query($cnx,"UPDATE cuenta SET saldo = $updateSaldoDestino WHERE nrocuenta = '$nrocuentadestino'");

//insertar transaccion 
mysqli_query($cnx,"INSERT INTO transaccion (nrocuentaorigen,nrocuentadestino,valor) VALUES ('$nrocuentaorigen','$nrocuentadestino','$valor')");

mysqli_close($cnx);

}
else{
	echo "Especificar los datos cuenta origen, cuenta destino, valor ";
}



 ?>
