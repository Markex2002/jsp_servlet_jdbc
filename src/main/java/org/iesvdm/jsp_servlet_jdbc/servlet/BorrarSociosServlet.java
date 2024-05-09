package org.iesvdm.jsp_servlet_jdbc.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAO;
import org.iesvdm.jsp_servlet_jdbc.dao.SocioDAOImpl;
import org.iesvdm.jsp_servlet_jdbc.model.Socio;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BorrarSociosServlet", value = "/BorrarSociosServlet")
public class BorrarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //USAMOS REQUEST.GETPARAMETER PARA RECIBIR EL ID, Y CON EL DAO, BORRAMOS AL USUARIO
        int id = Integer.parseInt(request.getParameter("codigo"));
        socioDAO.delete(id);

        //PREPARAMOS EL LISTADO Y LO PONEMOS COMO ATRIBUTO PARA QUE LISTADOJSP PUEDA USARLO OTRA VEZ
        List<Socio> listado = this.socioDAO.getAll();
        request.setAttribute("listado", listado);

        //CREAMOS UNA REDIRECCION, QUE ADEMAS, MANDARA LOS OBJETOS QUE HEMOS DEFINIDO CON EL REQUEST
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
        dispatcher.forward(request, response);
    }
}