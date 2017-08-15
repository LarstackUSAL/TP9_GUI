# TP9_GUI - Programacion avanzada 2017

Desarrollar una aplicación JAVA con OO, componentes GUI, modelo de eventos y MVC, para el ingreso, consulta y actualización de una entidad seleccionada a su preferencia, que no haya sido desarrollada en clase o en otro trabajo práctico.
 
La aplicación debe incluir un menú desplegable con dos opciones principales: Sistemas y Operaciones. 
	• Sistemas. Incluirá dos sub opciones:
		? Acerca de: donde se incluye información del aplicativo: nombre, versión, año, autores.
		? Salir: para finalizar el uso del aplicativo, previa confirmación con el usuario.
	• Operaciones. Incluirá, al menos tres sub-opciones:
		? Ingreso.
		? Consulta y actualización.
		? Consulta masiva.
 
Sub-opciones:
Ingreso. Permite el ingreso y registración en uno o más archivos TXT plano separado por “;” (punto y coma), de toda la información relacionada con la entidad elegida.
La pantalla gráfica para el ingreso de toda la información debe incluir:
	- Título para la ventana activa : JFrame
	- Cuadro de texto : JTextArea
	- Etiquetas : JLabel
	- Leyenda o comentario extenso no editable, con longitud mayor a 3 renglones : JTextArea
	- Dos listas desplegables, donde la segunda sea dependiente de la primera : JComboBox
	- Listas estática y dinámica con selección múltiple en ambas : Jlist
	- Botones de opciones, dentro de un panel específico : JRadioButton
	- Casillas de verificación : JCheckBox
	- Botones de acciones: Aceptar y Cancelar : JButton
Al “Aceptar” el ingreso, el sistema deberá validar que, al menos, 3 componentes de distinto tipo estén informados. Luego, se agregará la información ingresada en el archivo TXT.
La información origen de todas las listas deberá residir en distintos archivos TXT. Para el caso de la lista desplegable dependiente, el archivo TXT tiene al menos tres columnas formateadas con ancho fijo, siendo la segunda columna, la que participa en la lista.
Al “Cancelar” el ingreso, luego que el usuario de confirmación a tal operación, el sistema blanqueará los campos ingresados.
 
Consulta y Actualización. El sistema deberá incluir una pantalla con, al menos, dos datos aplicados como criterio de búsqueda de la información a modificar, a eliminar y/o a consultar. 
Una vez ingresado los datos a buscar, el usuario presionará el botón “Consultar” y el sistema la localizará entre la información registrada. Si la encuentra, mostrará el resto de la información en formato no editable. De lo contario, un mensaje de error lanzando una excepción propia.
Al visualizar la información completa de la entidad, el usuario puede seleccionar: Modificación o Anulación. 
Para el primer caso, “Modificación”, los componentes de la pantalla pasarán a modo editable para que pueda ser modificada la información visualizada. Y luego, seleccionará Aceptar o Cancelar, de acuerdo a lo deseado.
Para el segundo caso, “Anulación”, el sistema confirmará tal operación y si es avalada por el usuario, se eliminará la información del archivo TXT donde reside.
 
Consulta Masiva. Esta pantalla contará con un cuadro de texto, donde el usuario completará la información a consultar. 
El sistema mostrará en una Grilla de Consulta de Registros, todos aquellos que coincidan total o parcialmente con el criterio de búsqueda ingresado. 
En caso, que el usuario no ingrese ningún filtro, se mostrarán la totalidad de los registros.
La pantalla deberá incluir la cantidad total de registros visualizados y la cantidad total de registros existentes.
 
El modelo de objetos debe incluir una entidad principal y una derivada con diversidad de tipos de datos miembros. Debe incluir también, al menos, dos clases secundarias asociadas, que se relacionen por agregación en un caso y por composición, en otro. Alguno de ellos, implementando cardinalidad múltiple. 
Presentar el diagrama de clases correspondiente en formato jpg.
 
Para el desarrollo de esta guía, se deberá hacer uso de colecciones.
  
Aclaraciones: 
	· No se aceptan trabajos prácticos ni correcciones incompletas. Aquellos que no cumplan con la totalidad de lo solicitado serán considerados como no entregado sin aviso alguno.
	· El plazo máximo de prórroga de entrega es de 72hs. desde la fecha de entrega indicada. Transcurrido dicho plazo y si correspondiese, los trabajos prácticos serán considerados para el recuperatorio.
