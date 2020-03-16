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
		try{
			ArrayList<Proyecto> p = proyectoMapper.getProyectos();
			/*System.out.println(p);
			System.out.println(p.get(0).getNombre());
			System.out.println(p.get(0).getMensajes().get(0).getContenido());
			System.out.println(p.get(0).getMensajes().get(1).getContenido());
			System.out.println(p.get(0).getParticipantes().size());
			Usuario u = usuarioMapper.getUser("j@mail.com");
			System.out.println(u.getInvitaciones().get(0).getRemitente().getNombre());
			System.out.println(u.getInvitaciones().get(1).getRemitente().getNombre());
			System.out.println(u.getInvitaciones().get(0).getProyecto().getNombre());
			System.out.println(u.getInvitaciones().get(1).getProyecto().getNombre());
			//System.out.println(u.getNombre());
			//System.out.println(u.getPasswd());*/
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
