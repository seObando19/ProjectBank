<?php 

if(isset($_REQUEST['nrocuenta']) && isset($_REQUEST['ident']) && isset($_REQUEST['saldo'])) 
{

$nrocuenta=$_REQUEST['nrocuenta'];
$ident=$_REQUEST['ident'];
$saldo=$_REQUEST['saldo'];

$cnx =  mysqli_connect("localhost","root","","bd_projectbank") or die("Ha sucedido un error inexperado en la conexion de la base de datos");
$result = mysqli_query($cnx,"select nrocuenta from cuenta where nrocuenta = '$nrocuenta'");
if (mysqli_num_rows($result)==0)
{	
	mysqli_query($cnx,"INSERT INTO cuenta (nrocuenta,ident,saldo) VALUES ('$nrocuenta','$ident','$saldo')");	
}
else
{
	echo "cuenta ya existe....";
}
mysqli_close($cnx);

}
else{
	echo "Ingrese todos los datos....";
}



 ?>
