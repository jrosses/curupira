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



public class Application extends Controller {

  @Inject
  private  SqlSessionFactory sessionFactory;

  public Result index() throws SQLException {
  try{
  
  
    }catch(Exception ex)
    {
     Logger.info("Erro no index =>"+ex.getMessage());
    }
    /*
    try {
    	
    	
    */
        return ok(index.render(""));
   // } finally {
    //  session.close();
    //}
  }
  
  public static Result main(String any) throws SQLException 
  {
	  return ok(main.render());
  }
	  
	  
   public static Result page1() throws SQLException
   {
	        return ok(page1.render());
   }
	  
	    public static Result page2() throws SQLException {
	    return ok(page2.render());
	  }
	  
	    public static Result color() throws SQLException {
	      return ok(color.render());
	  }
	  
	    public static Result users() throws SQLException {
	       return ok(users.render());
	  }
	  
	    public static Result user() throws SQLException {
	       return ok(user.render());
	  }

  
  public static Result phones(String phoneId) throws IOException 
  {
	    Logger.info("Está usando phones no Apllication");
	    File jsonFile = Play.application().getFile("public/phones/dell-streak-7.json");
	    String json = FileUtils.readFileToString(jsonFile);
	    return ok(json).as("application/json");
  }
  public static Result wines() throws IOException 
  {
          String json="[{\"id\":\"9\",\"name\":\"BLOCK NINE\",\"year\":\"2009\",\"grapes\":\"Pinot Noir\",\"country\":\"USA\",\"region\":\"California\",\"description\":\"With hints of ginger and spice, this wine makes an excellent complement to light appetizer and dessert fare for a holiday gathering.\",\"picture\":\"block_nine.jpg\"}]";
          return ok(json).as("application/json");
  }
  
  public static Result wines(String wineId) throws IOException {
      String json="[{\"id\":\"9\",\"name\":\"BLOCK NINE\",\"year\":\"2009\",\"grapes\":\"Pinot Noir\",\"country\":\"USA\",\"region\":\"California\",\"description\":\"With hints of ginger and spice, this wine makes an excellent complement to light appetizer and dessert fare for a holiday gathering.\",\"picture\":\"block_nine.jpg\"}]";
      return ok(json).as("application/json");
  }
  

}