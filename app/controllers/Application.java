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

public class Application extends Controller {

  @Inject
  private SqlSessionFactory sessionFactory;

  public Result index() throws SQLException {
    SqlSession session = sessionFactory.openSession();
    try {
    	
    	UsuarioMapper mapper = session.getMapper(UsuarioMapper.class);
    	Usuario usuario = mapper.selectUsuario(1);
    	//Usuario usuario=session.selectOne("br.com.systhemis.curupira.model.mapper.UsuarioMapper.selectUsuario", 1);
        return ok(index.render("O Nome do Usuário é: " + usuario.toString()));
    } finally {
      session.close();
    }
  }

}