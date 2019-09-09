<?PHP 
$ident=$_REQUEST['ident'];

$cnx = mysqli_connect("localhost","root","","bd_projectbank") or die ("ha sucedido un error inesperado en la conexion a la base de datos");
$result = mysqli_query($cnx,"select * from cliente where ident= '$ident'");

while($fila=$result->fetch_array()){
    $usuario[]=array_map('utf8_encode', $fila);
}
echo json_encode($usuario);

mysqli_close($cnx);


?>