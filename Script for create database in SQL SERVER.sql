USE [master]
GO

/****** Object:  Database [CorralesTernero2]    Script Date: 01/10/2020 22:25:11 ******/
CREATE DATABASE [CorralesTernero2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CorralesTernero2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\CorralesTernero2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'CorralesTernero_log2', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\CorralesTernero_log2.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CorralesTernero2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [CorralesTernero2] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [CorralesTernero2] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [CorralesTernero2] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [CorralesTernero2] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [CorralesTernero2] SET ARITHABORT OFF 
GO

ALTER DATABASE [CorralesTernero2] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [CorralesTernero2] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [CorralesTernero2] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [CorralesTernero2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [CorralesTernero2] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [CorralesTernero2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [CorralesTernero2] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [CorralesTernero2] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [CorralesTernero2] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [CorralesTernero2] SET  ENABLE_BROKER 
GO

ALTER DATABASE [CorralesTernero2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [CorralesTernero2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [CorralesTernero2] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [CorralesTernero2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [CorralesTernero2] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [CorralesTernero2] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [CorralesTernero2] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [CorralesTernero2] SET RECOVERY FULL 
GO

ALTER DATABASE [CorralesTernero2] SET  MULTI_USER 
GO

ALTER DATABASE [CorralesTernero2] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [CorralesTernero2] SET DB_CHAINING OFF 
GO

ALTER DATABASE [CorralesTernero2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [CorralesTernero2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [CorralesTernero2] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [CorralesTernero2] SET QUERY_STORE = OFF
GO

ALTER DATABASE [CorralesTernero2] SET  READ_WRITE 
GO

USE [CorralesTernero2]
GO

/****** Object:  Table [dbo].[Alimentos]    Script Date: 01/10/2020 22:25:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Alimentos](
	[AliId] [int] IDENTITY(1,1) NOT NULL,
	[AliNombre] [varchar](50) NOT NULL,
	[Existencia] [numeric](7, 2) NOT NULL,
 CONSTRAINT [PK_Alimentos] PRIMARY KEY CLUSTERED 
(
	[AliId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Estados]    Script Date: 01/10/2020 22:26:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Estados](
	[EdoId] [int] IDENTITY(1,1) NOT NULL,
	[EdoNombre] [varchar](30) NOT NULL,
 CONSTRAINT [PK_Estados] PRIMARY KEY CLUSTERED 
(
	[EdoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Ciudades]    Script Date: 01/10/2020 22:26:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Ciudades](
	[CiuId] [int] IDENTITY(1,1) NOT NULL,
	[CiuNombre] [varchar](50) NOT NULL,
	[EdoId] [int] NOT NULL,
 CONSTRAINT [PK_Ciudades] PRIMARY KEY CLUSTERED 
(
	[CiuId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Ciudades]  WITH CHECK ADD  CONSTRAINT [FK_Ciudades_Estados] FOREIGN KEY([EdoId])
REFERENCES [dbo].[Estados] ([EdoId])
GO

ALTER TABLE [dbo].[Ciudades] CHECK CONSTRAINT [FK_Ciudades_Estados]
GO

/****** Object:  Table [dbo].[ColoresMusculo]    Script Date: 01/10/2020 22:27:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ColoresMusculo](
	[ColorMusculoId] [int] IDENTITY(1,1) NOT NULL,
	[ColNombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_ColoresMusculo] PRIMARY KEY CLUSTERED 
(
	[ColorMusculoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Corrales]    Script Date: 01/10/2020 22:28:19 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Corrales](
	[CorId] [int] IDENTITY(1,1) NOT NULL,
	[CorNombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Corrales] PRIMARY KEY CLUSTERED 
(
	[CorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Grasas]    Script Date: 01/10/2020 22:32:12 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Grasas](
	[GraId] [int] IDENTITY(1,1) NOT NULL,
	[GraNombre] [varchar](25) NOT NULL,
	[GraDesc] [varchar](150) NOT NULL,
 CONSTRAINT [pk_grasas_graid] PRIMARY KEY CLUSTERED 
(
	[GraId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Crias]    Script Date: 01/10/2020 22:28:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Crias](
	[CriId] [int] IDENTITY(1,1) NOT NULL,
	[CriPeso] [numeric](6, 2) NOT NULL,
	[CriCantGrasa] [numeric](6, 2) NOT NULL,
	[ColorMusculoId] [int] NOT NULL,
	[CiuId] [int] NOT NULL,
	[AliId] [int] NOT NULL,
	[CriSalud] [bit] NOT NULL,
	[CorId] [int] NOT NULL,
	[CriFechaIngreso] [datetime] NOT NULL,
	[CriFechaEgreso] [datetime] NULL,
	[graid] [int] NOT NULL,
	[Estatus] [bit] NOT NULL,
 CONSTRAINT [PK_Crias] PRIMARY KEY CLUSTERED 
(
	[CriId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Crias] ADD  CONSTRAINT [dc_crias_alimentacion]  DEFAULT ('') FOR [AliId]
GO

ALTER TABLE [dbo].[Crias] ADD  CONSTRAINT [dc_crias_crisalud]  DEFAULT ((1)) FOR [CriSalud]
GO

ALTER TABLE [dbo].[Crias] ADD  CONSTRAINT [DC_Crias_CriFechaIngreso]  DEFAULT (getdate()) FOR [CriFechaIngreso]
GO

ALTER TABLE [dbo].[Crias] ADD  CONSTRAINT [dc_crias_estatus]  DEFAULT ((1)) FOR [Estatus]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [FK_Crias_AliId] FOREIGN KEY([AliId])
REFERENCES [dbo].[Alimentos] ([AliId])
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [FK_Crias_AliId]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [FK_Crias_CiuId] FOREIGN KEY([CiuId])
REFERENCES [dbo].[Ciudades] ([CiuId])
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [FK_Crias_CiuId]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [FK_Crias_Corrales] FOREIGN KEY([CorId])
REFERENCES [dbo].[Corrales] ([CorId])
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [FK_Crias_Corrales]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [FK_Crias_CriColorMusculo] FOREIGN KEY([ColorMusculoId])
REFERENCES [dbo].[ColoresMusculo] ([ColorMusculoId])
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [FK_Crias_CriColorMusculo]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [fk_crias_graid] FOREIGN KEY([graid])
REFERENCES [dbo].[Grasas] ([GraId])
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [fk_crias_graid]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [CC_Crias_CriCantGrasa] CHECK  (([cricantgrasa]>(0)))
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [CC_Crias_CriCantGrasa]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [CC_Crias_CriFechaEgreso] CHECK  (([crifechaegreso]>[crifechaingreso]))
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [CC_Crias_CriFechaEgreso]
GO

ALTER TABLE [dbo].[Crias]  WITH CHECK ADD  CONSTRAINT [CC_Crias_CriPeso] CHECK  (([CriPeso]>(0)))
GO

ALTER TABLE [dbo].[Crias] CHECK CONSTRAINT [CC_Crias_CriPeso]
GO

/****** Object:  Table [dbo].[Medicamentos]    Script Date: 01/10/2020 22:34:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Medicamentos](
	[MedId] [int] IDENTITY(1,1) NOT NULL,
	[MedNombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Medicamentos] PRIMARY KEY CLUSTERED 
(
	[MedId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Dietas]    Script Date: 01/10/2020 22:33:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Dietas](
	[DieId] [int] IDENTITY(1,1) NOT NULL,
	[Comentario] [varchar](150) NOT NULL,
	[MedID] [int] NOT NULL,
 CONSTRAINT [PK_Dietas] PRIMARY KEY CLUSTERED 
(
	[DieId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Dietas]  WITH CHECK ADD  CONSTRAINT [FK_Dietas_Medicamentos] FOREIGN KEY([MedID])
REFERENCES [dbo].[Medicamentos] ([MedId])
GO

ALTER TABLE [dbo].[Dietas] CHECK CONSTRAINT [FK_Dietas_Medicamentos]
GO

/****** Object:  Table [dbo].[Cuarentena]    Script Date: 01/10/2020 22:32:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Cuarentena](
	[CuaFechaIngreso] [datetime] NOT NULL,
	[CriId] [int] NOT NULL,
	[CuaFechaEgreso] [datetime] NULL,
	[DieID] [int] NOT NULL,
 CONSTRAINT [PK_Cuarentena] PRIMARY KEY CLUSTERED 
(
	[CuaFechaIngreso] ASC,
	[CriId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Cuarentena] ADD  CONSTRAINT [DC_Cuarentena_CuaFechaIngreso]  DEFAULT (getdate()) FOR [CuaFechaIngreso]
GO

ALTER TABLE [dbo].[Cuarentena]  WITH CHECK ADD  CONSTRAINT [FK_Cuarentena_CriId] FOREIGN KEY([CriId])
REFERENCES [dbo].[Crias] ([CriId])
GO

ALTER TABLE [dbo].[Cuarentena] CHECK CONSTRAINT [FK_Cuarentena_CriId]
GO

ALTER TABLE [dbo].[Cuarentena]  WITH CHECK ADD  CONSTRAINT [FK_Cuarentena_Dietas] FOREIGN KEY([DieID])
REFERENCES [dbo].[Dietas] ([DieId])
GO

ALTER TABLE [dbo].[Cuarentena] CHECK CONSTRAINT [FK_Cuarentena_Dietas]
GO

ALTER TABLE [dbo].[Cuarentena]  WITH CHECK ADD  CONSTRAINT [CC_Cuarentena_CriFechaEgreso] CHECK  (([cuafechaegreso]>[cuafechaingreso]))
GO

ALTER TABLE [dbo].[Cuarentena] CHECK CONSTRAINT [CC_Cuarentena_CriFechaEgreso]
GO

ALTER TABLE [dbo].[Cuarentena]  WITH CHECK ADD  CONSTRAINT [CC_Cuarentena_CriFechaIngreso] CHECK  (([cuafechaingreso]>=getdate()))
GO

ALTER TABLE [dbo].[Cuarentena] CHECK CONSTRAINT [CC_Cuarentena_CriFechaIngreso]
GO

/****** Object:  Table [dbo].[InventarioSensores]    Script Date: 01/10/2020 22:34:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[InventarioSensores](
	[CiuID] [int] NOT NULL,
	[SenID] [int] IDENTITY(1,1) NOT NULL,
	[CriID] [int] NULL,
 CONSTRAINT [PK_InventarioSensores] PRIMARY KEY CLUSTERED 
(
	[CiuID] ASC,
	[SenID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[InventarioSensores]  WITH CHECK ADD  CONSTRAINT [FK_InventarioSensores_Crias] FOREIGN KEY([CriID])
REFERENCES [dbo].[Crias] ([CriId])
GO

ALTER TABLE [dbo].[InventarioSensores] CHECK CONSTRAINT [FK_InventarioSensores_Crias]
GO

/****** Object:  Table [dbo].[ReportaSensores]    Script Date: 01/10/2020 22:35:04 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ReportaSensores](
	[CriId] [int] NOT NULL,
	[Fecha] [datetime] NOT NULL,
	[FrecuenciaCardiaca] [numeric](5, 2) NOT NULL,
	[FrecuenciaRespiratoria] [numeric](5, 2) NOT NULL,
 CONSTRAINT [PK_ReportaSensores] PRIMARY KEY CLUSTERED 
(
	[CriId] ASC,
	[Fecha] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[ReportaSensores]  WITH CHECK ADD  CONSTRAINT [FK_ReportaSensores_CriId] FOREIGN KEY([CriId])
REFERENCES [dbo].[Crias] ([CriId])
GO

ALTER TABLE [dbo].[ReportaSensores] CHECK CONSTRAINT [FK_ReportaSensores_CriId]
GO

ALTER TABLE [dbo].[ReportaSensores]  WITH NOCHECK ADD  CONSTRAINT [CC_ReportaSensores_Fecha] CHECK  (([fecha]>=getdate()))
GO

ALTER TABLE [dbo].[ReportaSensores] NOCHECK CONSTRAINT [CC_ReportaSensores_Fecha]
GO

ALTER TABLE [dbo].[ReportaSensores]  WITH CHECK ADD  CONSTRAINT [CC_ReportaSensores_FrecuenciaCardiaca] CHECK  (([frecuenciacardiaca]>=(0)))
GO

ALTER TABLE [dbo].[ReportaSensores] CHECK CONSTRAINT [CC_ReportaSensores_FrecuenciaCardiaca]
GO

ALTER TABLE [dbo].[ReportaSensores]  WITH CHECK ADD  CONSTRAINT [CC_ReportaSensores_FrecuenciaRespiratoria] CHECK  (([frecuenciarespiratoria]>=(0)))
GO

ALTER TABLE [dbo].[ReportaSensores] CHECK CONSTRAINT [CC_ReportaSensores_FrecuenciaRespiratoria]
GO

/****** Object:  View [dbo].[CriasEnfermasView]    Script Date: 01/10/2020 22:37:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[CriasEnfermasView]
AS
	SELECT 
		c.CriId 'ID Cria', 
		c.cripeso 'Peso',
		c.criCantGrasa 'Cant. Grasa',
		cm.ColNombre 'Col. Musculo',
		e.EdoNombre 'Estado',
	    ci.CiuNombre 'Ciudad',
		CONVERT(VARCHAR(30),cu.CuaFechaIngreso,105) 'Fec. Ingreso',
		DATEDIFF(dd,cu.CuaFechaIngreso,GETDATE()) 'Dias'
		 
	FROM crias c
		inner join cuarentena cu ON c.CriId=cu.CriId AND cu.CuaFechaEgreso IS NULL
		inner join Corrales co ON c.CorId = co.CorId
		inner join Ciudades ci ON ci.CiuId=c.CiuId
	    inner join Estados e ON ci.EdoId=e.EdoId
		inner join ColoresMusculo cm ON cm.ColorMusculoId=c.ColorMusculoId
	WHERE
		C.Estatus=1 AND C.CriSalud=1
GO

/****** Object:  View [dbo].[CriasInsertadasHoyView]    Script Date: 01/10/2020 22:38:11 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[CriasInsertadasHoyView] 
as
	SELECT 
		Cri.CriID 'ID Cria', 
		Cri.CriPeso 'Peso', 
		Cri.CriCantGrasa 'Cant. Grasa', 
		ColNombre 'Col. Musculo', 
		CorNombre 'Corral', 
		EdoNombre 'Estado', 
		CiuNombre 'Ciudad', 
		GraNombre 'Tipo Grasa',
		CASE WHEN Cri.GraId = 3 THEN CONVERT(VARCHAR(5),I.SenID) ELSE 'N/A' END 'Sensor'
	FROM CRIAS Cri
		INNER JOIN Ciudades Ciu ON Cri.CiuId = Ciu.CiuId
		INNER JOIN Estados E ON E.EdoId = Ciu.EdoId
		INNER JOIN Corrales Cor ON Cor.CorId = Cri.CorId
		INNER JOIN ColoresMusculo CM ON CM.ColorMusculoId = Cri.ColorMusculoId
		INNER JOIN Grasas G ON G.GraId = Cri.graid
		LEFT JOIN InventarioSensores I ON I.CriID = Cri.CriId
	WHERE CriFechaIngreso >= CONVERT(VARCHAR(8),GETDATE(),112)
GO

/****** Object:  View [dbo].[CriasListasView]    Script Date: 01/10/2020 22:38:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[CriasListasView]
AS
	SELECT 
		C.CriId 'ID Cria',
		Y.CiuNombre 'Ciudad', 
		S.CorNombre 'Corral' ,
		CONVERT(VARCHAR(30) , C.CriFechaIngreso , 105) 'Ingreso', 
		DATEDIFF(MM , C.CriFechaIngreso , GETDATE()) 'Meses' 
	FROM 
		Crias C
		INNER JOIN Ciudades Y ON Y.CiuId=C.CiuId
		INNER JOIN Corrales S ON S.CorId=C.CorId
	WHERE 
		C.Estatus=1 AND
		DATEDIFF( MM , C.CriFechaIngreso , GETDATE() ) >= 5  AND C.CorId <> 5
		AND NOT EXISTS ( SELECT * FROM ReportaSensores R 
						 WHERE R.CriId = C.CriId
						 AND R.Fecha >= GETDATE() - 7 
						 AND R.FrecuenciaCardiaca > 60
						 AND R.FrecuenciaRespiratoria > 10 )	
GO

/****** Object:  View [dbo].[CriasPorEnfermarseView]    Script Date: 01/10/2020 22:38:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[CriasPorEnfermarseView]
AS
	SELECT  
		C.CriId 'ID Cria', 
		COR.CorNombre 'Corral', 
		CIU.CiuNombre 'Ciudad',
		CONVERT(VARCHAR(50), DATEDIFF(MM, C.CriFechaIngreso, GETDATE() ) ) ' Meses' 
	FROM Crias C
		INNER JOIN Corrales COR ON COR.CorId=C.CorId AND C.CorId<>5
		INNER JOIN Ciudades CIU ON CIU.CiuId=C.CiuId
	WHERE C.Estatus=1 AND C.CriSalud=1 AND C.CorId <> 5 AND
	EXISTS ( SELECT * FROM ReportaSensores R 
				   WHERE C.CriId = R.CriId 
						 AND R.Fecha >= GETDATE() - 7
						 AND ( FrecuenciaCardiaca > 60 
						 OR FrecuenciaRespiratoria > 10 ) )
GO

/****** Object:  View [dbo].[ReportaSensoresView]    Script Date: 01/10/2020 22:39:01 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[ReportaSensoresView]
AS
	SELECT
		CriId,
		CONVERT(VARCHAR(30),Fecha,113) 'Fecha',
		FrecuenciaCardiaca,
		FrecuenciaRespiratoria
	FROM 
		ReportaSensores
GO

/****** Object:  View [dbo].[ViewCriasQueDuraronMasDe5Meses]    Script Date: 01/10/2020 22:39:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

  
CREATE VIEW [dbo].[ViewCriasQueDuraronMasDe5Meses]   
AS  
  
 SELECT   
  C.CriID 'ID Cria',  
  CO.CorNombre 'Corral',   
  C.cripeso 'Peso',  
  C.cricantgrasa 'Cant. de Grasa',  
  CM.colnombre 'Color del musculo',  
  G.GraNombre 'Tipo de grasa',CI.CiuNombre 'Ciudad',   
  CONVERT(VARCHAR(30),C.CriFechaIngreso,105) 'Ingreso',  
  CONVERT(VARCHAR(30),C.CriFechaEgreso,105) 'Egreso',  
  (CASE WHEN C.CriSalud=0 THEN 'Sacrificada' ELSE 'Avanzo' END) 'Motivo',  
  DATEDIFF(MM,C.CriFechaIngreso,GETDATE()) 'Meses',  
  (CASE WHEN C.CriSalud = 0 THEN 'MUERTA' ELSE 'VIVA' END) 'ULTIMO ESTATUS'  
 FROM Crias C  
  INNER JOIN coloresmusculo CM ON C.ColorMusculoId=CM.ColorMusculoId  
  INNER JOIN grasas G ON C.graid=G.GraId  
  INNER JOIN Alimentos A ON A.AliId=C.AliId  
  INNER JOIN Ciudades CI ON CI.CiuId=C.CiuId  
  INNER JOIN Corrales CO ON CO.corid=C.corid  
  LEFT JOIN Cuarentena CU ON CU.CriId=C.CriId AND CU.CuaFechaEgreso IS NOT NULL  
 WHERE   
  (C.crifechaegreso IS NOT NULL)   
  AND DATEDIFF(MM,C.CriFechaIngreso,C.CriFechaEgreso) > 5  
GO

/****** Object:  StoredProcedure [dbo].[pa_CalculaTipoGrasa]    Script Date: 01/10/2020 22:39:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[pa_CalculaTipoGrasa]
	@cripeso numeric(6,2),
	@cricantgrasa numeric(6,2),
	@colormusculoid int,
	@graid INT OUTPUT
as

	IF @Colormusculoid = 1
	BEGIN
		IF @cripeso > 8000
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa < 2000 THEN 1
				WHEN @cricantgrasa BETWEEN 2000 AND 4000 THEN 2
				WHEN @cricantgrasa BETWEEN 4000 AND 6000 THEN 3
				WHEN @cricantgrasa > 6000 THEN 4
			END
		END

		IF @Cripeso BETWEEN 3000 AND 8000
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa > 8000 THEN 1
				WHEN @cricantgrasa BETWEEN 1000 AND 2500 THEN 4
				WHEN @cricantgrasa BETWEEN 2500 AND 3000 THEN 3
				WHEN @cricantgrasa < 1000 OR  @cricantgrasa BETWEEN 3000 AND 8000 THEN 2
			END
		END

		IF @cripeso < 3000
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa < 1500 THEN 1
				WHEN @cricantgrasa > 7000 OR @cricantgrasa BETWEEN 1500 AND 2500 THEN 2
				WHEN @cricantgrasa BETWEEN 2500 AND 5000 THEN 3
				WHEN @cricantgrasa BETWEEN 5000 AND 7000 THEN 4
			END
		END
	END
	
	IF @colormusculoid = 2
	BEGIN
		IF @CRIPESO < 1500
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa > 8000 THEN 1
				WHEN @cricantgrasa < 2000 THEN 3
				WHEN @cricantgrasa BETWEEN 2000 AND 8000 THEN 4
			END
		END

		IF @cripeso BETWEEN 1500 AND 5000
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa < 1500 OR @cricantgrasa > 9000 THEN 1
				WHEN @cricantgrasa BETWEEN 1500 AND 4000 THEN 3
				WHEN @cricantgrasa BETWEEN 4000 AND 9000 THEN 4
			END
		END

		IF @cripeso > 5000
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa > 9000 THEN 1
				WHEN @cricantgrasa < 1500 OR @cricantgrasa BETWEEN 7000 AND 9000 THEN 2
				WHEN @cricantgrasa BETWEEN 1500 AND 7000 THEN 4
			END
		END
	END

	IF @colormusculoid = 3
	BEGIN
		IF @cripeso < 5000
		BEGIN
			SELECT @graid = CASE 
				WHEN @cricantgrasa < 5000 THEN 1
				WHEN @cricantgrasa >= 5000 THEN 4
			END
		END

		IF @cripeso >= 5000
		BEGIN
			SELECT @graid = CASE
				WHEN @cricantgrasa < 6000 THEN 1
				WHEN @cricantgrasa < 6000 AND @cricantgrasa BETWEEN 1500 AND 2000 THEN 2
				WHEN @cricantgrasa >= 6000 THEN 4
			END
		END
	END

GO

/****** Object:  StoredProcedure [dbo].[pa_darSalida]    Script Date: 01/10/2020 22:39:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE procedure [dbo].[pa_darSalida]
as
	BEGIN TRAN
		UPDATE Crias SET Estatus = 0 , CriFechaEgreso = GETDATE() 
		WHERE CriID in ( SELECT [ID Cria] FROM CriasListasView )
	COMMIT TRAN
GO

/****** Object:  StoredProcedure [dbo].[pa_GeneraSensores]    Script Date: 01/10/2020 22:40:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[pa_GeneraSensores]
AS
BEGIN TRAN
	SET NOCOUNT ON
	ALTER TABLE ReportaSensores NOCHECK CONSTRAINT CC_ReportaSensores_Fecha
	DECLARE @CriID INT
	DECLARE @Fecha DATETIME = GETDATE()-7
	DECLARE @FrecuenciaCardiaca NUMERIC(5,2)
	DECLARE @FrecuenciaRespiratoria NUMERIC(5,2)

	SELECT TOP 1 @CriID=CriID FROM Crias WITH(UPDLOCK)
	WHERE GraID=3
	ORDER BY CriID
	
	DECLARE @MaxCriID INT
	SELECT TOP 1 @MaxCriID=CriID FROM Crias
	WHERE GraID=3
	ORDER BY CriID DESC

	SELECT CriID FROM Crias
	WHERE GraID=3
	ORDER BY CriID DESC

	SELECT @CriID, @MaxCriID
	
	WHILE @CriID <> @MaxCriID
	BEGIN
		WHILE @Fecha < GETDATE()
		BEGIN
			SET @FrecuenciaCardiaca = FLOOR(RAND()*(99-20)+20)
			SET @FrecuenciaRespiratoria = FLOOR(RAND()*(99-20)+20)
			INSERT INTO ReportaSensores VALUES ( @CriID , @Fecha , @FrecuenciaCardiaca , @FrecuenciaRespiratoria )
			PRINT 'SE INSERTO '+CONVERT(VARCHAR(50),@Fecha )
			SET @Fecha = @Fecha + 1
		END

		SELECT TOP 1 @CriID = CriID FROM Crias 
		WHERE CriID > @CriID AND GraID = 3
		ORDER BY CriID

	END
COMMIT TRAN
GO

/****** Object:  StoredProcedure [dbo].[pa_InsertaCrias]    Script Date: 01/10/2020 22:40:34 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

  
CREATE PROCEDURE [dbo].[pa_InsertaCrias]  
 @CriPeso NUMERIC(6,2),  
 @CriCantGrasa NUMERIC(6,2),  
 @ColorMusculoId INT,  
 @CiuId INT,  
 @CorId INT,  
 @GraId INT,  
 @CriId INT OUTPUT  
AS  
 BEGIN TRAN  

	SET TRANSACTION ISOLATION LEVEL SERIALIZABLE

	INSERT Crias (CriPeso,CriCantGrasa,ColorMusculoId,CiuId,AliId,CorId,graid)
	VALUES(@CriPeso,@CriCantGrasa,@ColorMusculoId,@CiuId,1,@CorId,@GraId)  
 
	SELECT @CriID = MAX(CriID) FROM Crias
	WHERE CorId = @CorId

 COMMIT TRAN  
GO

/****** Object:  StoredProcedure [dbo].[pa_insertarDieta]    Script Date: 01/10/2020 22:40:51 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[pa_insertarDieta]
	@Medicamento INT,
	@Comentario VARCHAR(150)
AS
BEGIN
	SET NOCOUNT ON
	BEGIN TRAN
		DECLARE @DieID INT
		INSERT INTO Dietas VALUES(@Comentario,@Medicamento)

		SELECT @DieID=DieID From Dietas 
		WHERE Comentario=@Comentario AND MedID=@Medicamento
	COMMIT TRAN
	RETURN @DieID 
END
GO

/****** Object:  StoredProcedure [dbo].[pa_liberarCria]    Script Date: 01/10/2020 22:41:03 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_liberarCria]
	@CriID INT,
	@CorId INT
AS
BEGIN
	SET NOCOUNT ON
	--Se manda a corral de sanas y alimentacion sana
	UPDATE crias SET CorId=@CorId,AliId=1
	WHERE CriId=@CriID

	UPDATE Cuarentena SET CuaFechaEgreso=GETDATE()
	WHERE CriId=@CriID AND CuaFechaEgreso IS NULL

	DELETE FROM Dietas 
	WHERE DieID = (SELECT TOP 1 DieID FROM Cuarentena 
				   WHERE CriId=@CriID ORDER BY CuaFechaIngreso DESC)
END
GO

/****** Object:  StoredProcedure [dbo].[pa_mandarCuarentena]    Script Date: 01/10/2020 22:42:01 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[pa_mandarCuarentena]
	@CriId INT,
	@Comentario VARCHAR(150),
	@Medicamento INT
AS
	BEGIN TRAN

		UPDATE Crias SET CorId=5, AliId=2 WHERE CriId=@CriId
		DECLARE @DieID INT
		EXEC @DieID = pa_insertarDieta @Medicamento, @Comentario
		INSERT Cuarentena(CriID,DieID)VALUES(@CriId,@DieID)

	COMMIT TRAN
GO

/****** Object:  StoredProcedure [dbo].[pa_sacrificarCria]    Script Date: 01/10/2020 22:42:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[pa_sacrificarCria]
	@CriId int
AS
BEGIN
	SET NOCOUNT ON;

	UPDATE Crias SET CriSalud=0,CriFechaEgreso=GETDATE()
	WHERE CriId=@CriId

	DECLARE @GraID INT
	SELECT @GraID = GraID FROM Crias
	WHERE CriID = @CriID

	IF(@GraID = 3)
	BEGIN
		UPDATE InventarioSensores SET CriID = NULL
		WHERE CriID=@CriID
	END

END
GO

/****** Object:  Index [IdxCorral]    Script Date: 01/10/2020 22:44:03 ******/
CREATE NONCLUSTERED INDEX [IdxCorral] ON [dbo].[Crias]
(
	[CorId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO







