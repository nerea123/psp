<?php

$id=$_REQUEST['id'];
$modelo=$_REQUEST['modelo'];
$objeto=array();

   $con=@mysql_connect("localhost","root","sistemas");
		if(!$con || !mysql_select_db("dbprueba", $con))
			die("error conectando a la base de datos ".mysql_error());
		else {
			if($modelo == "articulo"){
				$sql="select * from articulo where id=$id";
				$res=mysql_query($sql,$con);
				while ($linea = mysql_fetch_array($res)) {
					$objeto['nombre']=$linea['nombre'];
					$objeto['categoria']=$linea['categoria'];
					$objeto['precio']=$linea['precio'];
					$objeto['id']=$linea['id'];
				}
			}
			if($modelo == "categoria"){
				$sql="select * from categoria where id=$id";
				$res=mysql_query($sql,$con);
				while ($linea = mysql_fetch_array($res)) {
					$objeto['nombre']=$linea['nombre'];
					$objeto['id']=$linea['id'];
				}
			}
			echo json_encode($objeto);
		}
?>