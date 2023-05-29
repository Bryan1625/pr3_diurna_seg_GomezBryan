package programacion3.parcial3.servidor.ejercicio3;

import java.io.IOException;

import programacion3.parcial3.servidor.ejercicio3.persistence.Persistencia;


public class MainServidor {
	
	static Universidad universidad;
	public static void main(String[] args) throws Exception {
		iniciarTrabajosGrado();
		Servidor miServidor = new Servidor(8081);
		
		try {
			miServidor.runServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void iniciarTrabajosGrado() throws IOException {
		Universidad universidad= new Universidad();
		TrabajoGrado trabajoGrado1 = new TrabajoGrado();
        trabajoGrado1.setTitulo("La influencia de la Revolución Francesa en Europa Occidental");
        trabajoGrado1.setFecha("2022-01-15");
        trabajoGrado1.setDescripcion("Este trabajo analiza cómo la Revolución Francesa impactó la política y la sociedad en Europa Occidental");
        universidad.getTrabajosGrado().add(trabajoGrado1);
        
        TrabajoGrado trabajoGrado2 = new TrabajoGrado();
        trabajoGrado2.setTitulo("Evaluación de riesgos geológicos en una zona sísmica activa");
        trabajoGrado2.setFecha("2021-11-30");
        trabajoGrado2.setDescripcion("Este trabajo de grado investiga y evalúa los riesgos geológicos asociados a una zona sísmica activa analizando los procesos geológicos");
        universidad.getTrabajosGrado().add(trabajoGrado2);
        
        TrabajoGrado trabajoGrado3 = new TrabajoGrado();
        trabajoGrado3.setTitulo("Análisis de patrones culturales en un sitio arqueológico precolombino");
        trabajoGrado3.setFecha("2023-04-0");
        trabajoGrado3.setDescripcion("Este estudio analiza los patrones culturales encontrados en un sitio arqueológico precolombino a través del análisis de artefactos estructuras y contextos arqueológicos");
        universidad.getTrabajosGrado().add(trabajoGrado3);
        
        TrabajoGrado trabajoGrado4 = new TrabajoGrado();
        trabajoGrado4.setTitulo("Evaluación de la eficacia de nuevos tratamientos contra el cáncer");
        trabajoGrado4.setFecha("2022-06-18");
        trabajoGrado4.setDescripcion("Este proyecto de investigación evalúa la eficacia de nuevos tratamientos contra el cáncer, analizando su impacto en la supervivencia de los pacientes, los efectos secundarios y la calidad de vida, con el objetivo de mejorar las opciones terapéuticas disponibles");
        universidad.getTrabajosGrado().add(trabajoGrado4);
        
        TrabajoGrado trabajoGrado5 = new TrabajoGrado();
        trabajoGrado5.setTitulo("Estudio de las propiedades cuánticas de materiales superconductores");
        trabajoGrado5.setFecha("2023-02-10");
        trabajoGrado5.setDescripcion("Este trabajo de grado investiga las propiedades cuánticas de materiales superconductores, analizando su comportamiento a bajas temperaturas y campos magnéticos");
        universidad.getTrabajosGrado().add(trabajoGrado5);
        
        TrabajoGrado trabajoGrado6 = new TrabajoGrado();
        trabajoGrado6.setTitulo("Impacto de la actividad física en la salud mental y el bienestar");
        trabajoGrado6.setFecha("2022-09-05");
        trabajoGrado6.setDescripcion("Este estudio examina el impacto de la actividad física en la salud mental y el bienestar de los individuos");
        universidad.getTrabajosGrado().add(trabajoGrado6);
        
        TrabajoGrado trabajoGrado7 = new TrabajoGrado();
        trabajoGrado7.setTitulo("Análisis de la influencia de las redes sociales en el aprendizaje de idiomas");
        trabajoGrado7.setFecha("2023-01-20");
        trabajoGrado7.setDescripcion(" Este trabajo investiga la influencia de las redes sociales en el aprendizaje de idiomas, examinando cómo las interacciones en plataformas digitales pueden facilitar el desarrollo de habilidades lingüísticas");
        universidad.getTrabajosGrado().add(trabajoGrado7);
        
        TrabajoGrado trabajoGrado8 = new TrabajoGrado();
        trabajoGrado8.setTitulo("Análisis de los efectos económicos de la globalización en un sector industrial");
        trabajoGrado8.setFecha("2022-03-12");
        trabajoGrado8.setDescripcion("Este estudio analiza los efectos económicos de la globalización en un sector industrial específico, evaluando cómo los procesos de apertura comercial");
        universidad.getTrabajosGrado().add(trabajoGrado8);
        
        TrabajoGrado trabajoGrado9 = new TrabajoGrado();
        trabajoGrado9.setTitulo("Optimización de diseños estructurales utilizando materiales sostenibles");
        trabajoGrado9.setFecha("2023-03-28");
        trabajoGrado9.setDescripcion("Este proyecto busca optimizar los diseños estructurales en ingeniería civil mediante el uso de materiales sostenibles, evaluando diferentes alternativas constructivas");
        universidad.getTrabajosGrado().add(trabajoGrado9);
        
        TrabajoGrado trabajoGrado10 = new TrabajoGrado();
        trabajoGrado10.setTitulo("Periodismo de investigación en la era digital: retos y oportunidades");
        trabajoGrado10.setFecha("2022-08-15");
        trabajoGrado10.setDescripcion("Este trabajo aborda los retos y oportunidades del periodismo de investigación en la era digital");
        universidad.getTrabajosGrado().add(trabajoGrado10);
        //1
        Autor autor1 = new Autor();
        autor1.setNombre("Sophia");
        autor1.setApellido("Smith");
        autor1.setCedula("10937462");
        autor1.setPrograma("Filosofia");
        autor1.setTitulo("Filosofa");
        universidad.getAutores().add(autor1);
        trabajoGrado1.getAutores().add(autor1);
        //2
        Autor autor2 = new Autor();
        autor2.setNombre("Liam");
        autor2.setApellido("Johnson");
        autor2.setCedula("198364652");
        autor2.setPrograma("Geologia");
        autor2.setTitulo("Geologo");
        universidad.getAutores().add(autor2);
        trabajoGrado2.getAutores().add(autor2);
        //3
        Autor autor3 = new Autor();
        autor3.setNombre("Olivia");
        autor3.setApellido("Williams");
        autor3.setCedula("120947420");
        autor3.setPrograma("Geologia");
        autor3.setTitulo("Geologa");
        universidad.getAutores().add(autor3);
        trabajoGrado2.getAutores().add(autor3);
        //4
        Autor autor4 = new Autor();
        autor4.setNombre("Noah");
        autor4.setApellido("Jones");
        autor4.setCedula("103957348");
        autor4.setPrograma("Arqueologia");
        autor4.setTitulo("Arqueologo");
        universidad.getAutores().add(autor4);
        trabajoGrado3.getAutores().add(autor4);
        //5
        Autor autor5 = new Autor();
        autor5.setNombre("Emma");
        autor5.setApellido("Brown");
        autor5.setCedula("243984723");
        autor5.setPrograma("Arqueologia");
        autor5.setTitulo("Arqueologa");
        universidad.getAutores().add(autor5);
        trabajoGrado3.getAutores().add(autor5);
        //6
        Autor autor6 = new Autor();
        autor6.setNombre("Jackson");
        autor6.setApellido("Davis");
        autor6.setCedula("12094637");
        autor6.setPrograma("Filosofia");
        autor6.setTitulo("Filosofa");
        universidad.getAutores().add(autor6);
        trabajoGrado1.getAutores().add(autor6);
        //7
        Autor autor7 = new Autor();
        autor7.setNombre("Ava");
        autor7.setApellido("Miller");
        autor7.setCedula("2429803");
        autor7.setPrograma("Medicina");
        autor7.setTitulo("Medica");
        universidad.getAutores().add(autor7);
        trabajoGrado4.getAutores().add(autor7);
        //8
        Autor autor8 = new Autor();
        autor8.setNombre("Aiden");
        autor8.setApellido("Wilson");
        autor8.setCedula("23098462");
        autor8.setPrograma("Medicina");
        autor8.setTitulo("Medico");
        universidad.getAutores().add(autor8);
        trabajoGrado4.getAutores().add(autor8);
        //9
        Autor autor9 = new Autor();
        autor9.setNombre("Isabella");
        autor9.setApellido("Garcia");
        autor9.setCedula("2456120");
        autor9.setPrograma("Fisica");
        autor9.setTitulo("Fisica");
        universidad.getAutores().add(autor9);
        trabajoGrado5.getAutores().add(autor9);
        //10
        Autor autor10 = new Autor();
        autor10.setNombre("Lucas");
        autor10.setApellido("Martinez");
        autor10.setCedula("2890347");
        autor10.setPrograma("Fisca");
        autor10.setTitulo("Fisico");
        universidad.getAutores().add(autor10);
        trabajoGrado5.getAutores().add(autor10);
        //11
        Autor autor11 = new Autor();
        autor11.setNombre("Santiago");
        autor11.setApellido("García");
        autor11.setCedula("12976483");
        autor11.setPrograma("Educacion Fisica Recreacion y Deporte");
        autor11.setTitulo("Licenciado en Educacion Fisica Recreacion y Deporte");
        universidad.getAutores().add(autor11);
        trabajoGrado6.getAutores().add(autor11);
        //12
        Autor autor12 = new Autor();
        autor12.setNombre("Valentina");
        autor12.setApellido("Rodríguez");
        autor12.setCedula("281047596");
        autor12.setPrograma("Educacion Fisica Recreacion y Deporte");
        autor12.setTitulo("Licenciada en Educacion Fisica Recreacion y Deporte");
        universidad.getAutores().add(autor12);
        trabajoGrado6.getAutores().add(autor12);
        //13
        Autor autor13 = new Autor();
        autor13.setNombre("Sofía");
        autor13.setApellido("López");
        autor13.setCedula("24135679");
        autor13.setPrograma("Lenguas Modernas");
        autor13.setTitulo("Licenciada en Lenguas Modernas");
        universidad.getAutores().add(autor13);
        trabajoGrado7.getAutores().add(autor13);
        //14
        Autor autor14 = new Autor();
        autor14.setNombre("Mateo");
        autor14.setApellido("Martínez");
        autor14.setCedula("248932715");
        autor14.setPrograma("Lenguas Modernas");
        autor14.setTitulo("Licenciado en Lenguas Modernas");
        universidad.getAutores().add(autor14);
        trabajoGrado7.getAutores().add(autor14);
        //15
        Autor autor15 = new Autor();
        autor15.setNombre("Sebastián");
        autor15.setApellido("González");
        autor15.setCedula("289017");
        autor15.setPrograma("Economia");
        autor15.setTitulo("Economista");
        universidad.getAutores().add(autor15);
        trabajoGrado8.getAutores().add(autor15);
        //16
        Autor autor16 = new Autor();
        autor16.setNombre("Camila");
        autor16.setApellido("Pérez");
        autor16.setCedula("241053864");
        autor16.setPrograma("Economia");
        autor16.setTitulo("Economista");
        universidad.getAutores().add(autor16);
        trabajoGrado8.getAutores().add(autor16);
        //17
        Autor autor17 = new Autor();
        autor17.setNombre("Leonardo");
        autor17.setApellido("Hernández");
        autor17.setCedula("24409857");
        autor17.setPrograma("Ingenieria Civil");
        autor17.setTitulo("Ingeniero Civil");
        universidad.getAutores().add(autor17);
        trabajoGrado9.getAutores().add(autor17);
        //18
        Autor autor18 = new Autor();
        autor18.setNombre("Valeria");
        autor18.setApellido("Sánchez");
        autor18.setCedula("286497105");
        autor18.setPrograma("Ingenieria Civil");
        autor18.setTitulo("Ingeniera Civil");
        universidad.getAutores().add(autor18);
        trabajoGrado9.getAutores().add(autor18);
        //19
        Autor autor19 = new Autor();
        autor19.setNombre("Nicolás");
        autor19.setApellido("Ramírez");
        autor19.setCedula("2810472");
        autor19.setPrograma("Periodismo");
        autor19.setTitulo("Periodista");
        universidad.getAutores().add(autor19);
        trabajoGrado10.getAutores().add(autor19);
        //20
        Autor autor20 = new Autor();
        autor20.setNombre("Matias");
        autor20.setApellido("Torres");
        autor20.setCedula("24489501");
        autor20.setPrograma("Periodismo");
        autor20.setTitulo("Periodista");
        universidad.getAutores().add(autor20);
        trabajoGrado10.getAutores().add(autor20);
        
        Persistencia.guardarTrabajosGrado(universidad.getTrabajosGrado());
        Persistencia.guardarAutores(universidad.getAutores());
	}
	
	

}
