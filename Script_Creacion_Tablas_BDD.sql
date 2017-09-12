USE [TP_Programacion_Avanzada]
GO

/****** Script Date: 08/09/2017 17:00:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Pasajeros]
(
	[id] [INT] IDENTITY(1,1) NOT NULL,	
	[nombre_apellido] [VARCHAR](30) NOT NULL,
	[fecha_nacimiento] [DATE] NOT NULL,
	[dni] [INT] NOT NULL, 
	[email] [VARCHAR](30) NOT NULL,
);

CREATE TABLE [dbo].[Facturas]
(
	[id] [INT] IDENTITY(1,1) NOT NULL,	
	[fecha] [DATE] NOT NULL,
	[tipo] [CHAR](1) NOT NULL,
	[numero] [INT] NOT NULL, 
	[importe] [DECIMAL](10,2) NOT NULL,
);

CREATE TABLE [dbo].[Hoteles]
(
	[id] [INT] IDENTITY(1,1) NOT NULL,	
	[nombre] [VARCHAR](30) NOT NULL,
	[importe] [DECIMAL](10,2) NOT NULL,
	[estrellas] [INT] NOT NULL, 
);

CREATE TABLE [dbo].[Horarios]
(
	[id] [INT] IDENTITY(1,1) NOT NULL,	
	[horario] [TIME] NOT NULL,
	[turno] [VARCHAR](10) NOT NULL
);

CREATE TABLE [dbo].[Localidades]
(
	[id] [INT] IDENTITY(1,1) NOT NULL,	
	[localidad] [VARCHAR](30) NOT NULL,
	[importe] [DECIMAL](10,2) NOT NULL
); 

CREATE TABLE [dbo].[Paquetes]
(
	[id] [INT] IDENTITY(1,1) NOT NULL,	
	[fecha_hora_salida] [DATETIME] NOT NULL,
	[cantidad_dias] [INT] NOT NULL, 
	[importe] [DECIMAL](10,2) NOT NULL,
	[tiene_seguro] [BIT] NOT NULL, --VALOR 1 (TRUE) Ó 0 (FALSE)
	[factura_id] [INT] NOT NULL,
	[quiere_visita_guiada] [BIT] NOT NULL, --VALOR 1 (TRUE) Ó 0 (FALSE)
	[quiere_abono_transporte_local] [BIT] NOT NULL, --VALOR 1 (TRUE) Ó 0 (FALSE)
	[es_paquete_con_estadia] [BIT] NOT NULL, --VALOR 1 (TRUE, PAQUETE CON ESTADIA) Ó 0 (FALSE, PAQUETE SIN ESTADIA)
	[hotel_id] [INT] NULL, --ACEPTA NULL PARA PAQUETES SIN ESTADIA, CASO CONTRARIO PAQUETE CON ESTADIA
	[es_pension_completa] [BIT] NULL, --VALOR 1 (TRUE) Ó 0 (FALSE), ACEPTA NULL PARA PAQUETES SIN ESTADIA, CASO CONTRARIO PAQUETE CON ESTADIA
);

CREATE TABLE [dbo].[LocalidadesPaquetes]
(
	[paquete_id] [INT] NOT NULL, --ID PAQUETE
	[localidad_id] [INT] NOT NULL, --ID LOCALIDAD
);

CREATE TABLE [dbo].[PasajerosPaquetes]
(
	[paquete_id] [INT] NOT NULL, --ID PAQUETE
	[pasajero_id] [INT] NOT NULL, --ID PASAJERO
);