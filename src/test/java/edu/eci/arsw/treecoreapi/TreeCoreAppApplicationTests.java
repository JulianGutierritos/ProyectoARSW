package edu.eci.arsw.treecoreapi;

import edu.eci.arsw.treecore.model.impl.Proyecto;
import edu.eci.arsw.treecore.model.impl.Usuario;
import edu.eci.arsw.treecore.persistence.mappers.ProyectoMapper;
import edu.eci.arsw.treecore.persistence.mappers.UsuarioMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TreecoreAPIApplication.class)
public class TreeCoreAppApplicationTests {

	@Autowired
	UsuarioMapper usuarioMapper;

	@Autowired
	ProyectoMapper proyectoMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void prueba() {
		System.out.println("lllllllllllllllllllll");
		System.out.println(usuarioMapper);
		System.out.println(proyectoMapper);
		try{
			ArrayList<Proyecto> p = proyectoMapper.getProyectos();
			System.out.println(p);
			Usuario u = usuarioMapper.getUser2("n@mail.com");
			System.out.println(u.getCorreo());
			System.out.println(u.getNombre());
			System.out.println(u.getPasswd());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
