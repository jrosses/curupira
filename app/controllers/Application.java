package controllers;
import java.sql.SQLException;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.systhemis.curupira.model.Usuario;
import br.com.systhemis.curupira.model.mapper.UsuarioMapper;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import play.*;



public class Application extends Controller {

  @Inject
  private SqlSessionFactory sessionFactory;

  public Result index() throws SQLException {
    SqlSession session = sessionFactory.openSession();
    try {
    	 Logger.info("Entrou na versão");
    	UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
    	Usuario usuario = mapper.selectUsuario(1);
    
        return ok(index.render("O Nome do Usuário é: " + usuario.toString()));
    } finally {
      session.close();
    }
  }
  
  public static Result phones(String phoneId) throws IOException {
	    Logger.info("Está usando phones no Apllication");
	    File jsonFile = Play.application().getFile("public/phones/dell-streak-7.json");
	    String json = FileUtils.readFileToString(jsonFile);
	    return ok(json).as("application/json");
	  }

}