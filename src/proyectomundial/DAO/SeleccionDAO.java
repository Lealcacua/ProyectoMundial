package proyectomundial.DAO;

import com.sun.jdi.connect.spi.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyectomundial.GUIManual;
import java.sql.PreparedStatement;
import java.util.Arrays;

import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean totalSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO j_leal5.seleccion (nombres, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> Selecciones() {

        String sql = "SELECT nombres, continente, dt, nacionalidad FROM j_leal5.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> SeleccionesBusqueda(String nombreSeleccion) {
        System.out.println("LLEGAMOS HASTA GETSELECCIONESBUSQUEDA");
        String sql = "SELECT nombres, continente, dt, nacionalidad FROM j_leal5.seleccion WHERE nombres LIKE ?";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            if (BasedeDatos.conexion == null) {
                // Mostrar un mensaje de error o lanzar una excepción
                System.out.println("No hay conexión a la base de datos");
                return selecciones;
            }
            // Preparar la consulta SQL y establecer el parámetro
            PreparedStatement stmt = BasedeDatos.conexion.prepareStatement(sql);
            stmt.setString(1, "%" + nombreSeleccion + "%");

            // Ejecutar la consulta y obtener el resultado
            ResultSet result = stmt.executeQuery();

            if (result != null) {
                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombres"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public int totalSele() {
        int totalPartidos = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("select count(*) as total_selecciones from j_leal5.seleccion");
            if (result.next()) {
                totalPartidos = result.getInt("total_selecciones");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return totalPartidos;
    }

    public DefaultTableModel seleccionesP2() {
        String[] columnas = {"Continente", "Total"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente, COUNT(*) AS total_selecciones FROM j_leal5.seleccion GROUP BY continente;");
            while (result.next()) {
                String continente = result.getString("continente");
                int total = result.getInt("total_selecciones");
                Object[] fila = {continente, total};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;

    }

    public int seleccionesP3() {
        int cantDT = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT COUNT(DISTINCT nacionalidad) AS dt FROM j_leal5.seleccion");
            if (result.next()) {
                cantDT = result.getInt("dt");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return cantDT;

    }
    
    public DefaultTableModel seleccionesP4() {
        String[] columnas = {"Nacionalidad", "DT"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT nacionalidad, COUNT(*) AS dt FROM j_leal5.seleccion GROUP BY nacionalidad ORDER BY dt DESC");
            while (result.next()) {
                String nacionalidad = result.getString("nacionalidad");
                int dt = result.getInt("dt");
                Object[] fila = {nacionalidad, dt};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;

    }
    
    public String[][] SeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = Selecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }
}
