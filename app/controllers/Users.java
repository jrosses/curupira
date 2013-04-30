package controllers;
import java.sql.SQLException;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.systhemis.curupira.model.Usuario;
import br.com.systhemis.curupira.model.mapper.UsuarioMapper;
import play.mvc.Controller;
import play.mvc.Result;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import views.html.*;
import play.*;



public class Users extends Controller {
	
	  @Inject
	  private  SqlSessionFactory sessionFactory;

		  public  Result all() 
		  {
			  
			  SqlSession session = sessionFactory.openSession();
			  UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
			  Usuario usuario = mapper.selectUsuario(1);
		      String json="[{\"id\":\""+usuario.getId()+"\",\"name\":\""+usuario.getNome()+"\"},{\"id\":\"2\",\"name\":\"Usuário 2\"}]";
	      return ok(json).as("application/json");
		  }
		  
		  public  Result find(String id) 
		  {
	      Logger.info("Entrou no Find =>"+id);
		  String json="[{\"id\":\"9\",\"name\":\"Usuário1\"}]";
	      return ok(json).as("application/json");
		  }
		  

}
