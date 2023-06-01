package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import proyectomundial.DAO.ResultadosDAO;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Resultados;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();
    ResultadosDAO resultadosDAO = new ResultadosDAO();

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();

        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");

        jPanelMain.removeAll();
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();

        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        selecciones = seleccionDAO.SeleccionesMatriz();

        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        resultados = resultadosDAO.getResultadosMatriz();

        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaResultados();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardSel() {

        jLabelTop.setText("Dash Selecciones");

        // JPanel totalSe = new JPanel();
        // JLabel totalSele = new JLabel();
        //  totalSele.setText("Total Selecciones: " + seleccionDAO.totalSele());
        //  totalSe.add(totalSele);
        JPanel totalSe = new JPanel();
        totalSe.setBackground(Color.CYAN);
        totalSe.setPreferredSize(new Dimension(300, 50));
        JLabel lblPaneltotalSele = new JLabel();
        lblPaneltotalSele.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + seleccionDAO.totalSele() + "</span><br>Total Selecciones</div></html>");
        totalSe.add(lblPaneltotalSele);

        JPanel tablaP2_T = new JPanel();
        tablaP2_T.setBackground(Color.CYAN);
        tablaP2_T.setPreferredSize(new Dimension(500, 30));
        JLabel lblPaneltablaP2_T = new JLabel();
        lblPaneltablaP2_T.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 10;'>" + "</span><br>Número de selecciones por continente</div></html>");
        tablaP2_T.add(lblPaneltablaP2_T);
        JTable tablaP2 = new JTable();
        tablaP2.setBackground(Color.CYAN);
        tablaP2.setPreferredSize(new Dimension(500, 80));
        tablaP2.setModel(seleccionDAO.seleccionesP2());

        JPanel panelSelDT = new JPanel();
        panelSelDT.setBackground(Color.CYAN);
        panelSelDT.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelSelDT = new JLabel();
        lblPanelSelDT.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + seleccionDAO.seleccionesP3() + "</span><br>Cantidad de nacionalidades diferentes de DT</div></html>");
        panelSelDT.add(lblPanelSelDT);

        JPanel tablaP4_T = new JPanel();
        tablaP4_T.setBackground(Color.CYAN);
        tablaP4_T.setPreferredSize(new Dimension(500, 30));
        JLabel lblPaneltablaP4_T = new JLabel();
        lblPaneltablaP4_T.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 10;'>" + "</span><br>Ranking de nacionalidades de DT</div></html>");
        tablaP4_T.add(lblPaneltablaP4_T);
        JTable tablaP4 = new JTable();
        tablaP4.setBackground(Color.CYAN);
        tablaP4.setPreferredSize(new Dimension(500, 200));
        tablaP4.setModel(seleccionDAO.seleccionesP4());

        jPanelMain.removeAll();
        //jPanelMain.add(a);
        jPanelMain.setBackground(Color.BLUE);
        jPanelMain.add(totalSe);
        jPanelMain.add(panelSelDT);
        jPanelMain.add(tablaP2_T);
        jPanelMain.add(tablaP2);
        jPanelMain.add(tablaP4_T);
        jPanelMain.add(tablaP4);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                accionDashboardRes();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() {
        jLabelTop.setText("Dash Resultados");
        JPanel panelT = new JPanel();
        panelT.setBackground(Color.CYAN);
        panelT.setPreferredSize(new Dimension(200, 50));
        JLabel lblPanelT = new JLabel();
        lblPanelT.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + resultadosDAO.totalPartidos() + "</span><br>Partidos Cargados</div></html>");
        panelT.add(lblPanelT);

        JPanel panelPROM = new JPanel();
        panelPROM.setBackground(Color.CYAN);
        panelPROM.setPreferredSize(new Dimension(200, 50));
        JLabel lblPanelPROM = new JLabel();
        lblPanelPROM.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + resultadosDAO.promedioGolePorPatidos() + "</span><br>Promedio de Goles</div></html>");
        panelPROM.add(lblPanelPROM);

        JPanel panelMasGoles = new JPanel();
        panelMasGoles.setBackground(Color.CYAN);
        panelMasGoles.setPreferredSize(new Dimension(300, 100));
        JLabel lblPanelMasGoles = new JLabel();
        panelMasGoles.add(lblPanelMasGoles);

        // Obtener el modelo de tabla con los partidos con más goles
        DefaultTableModel MasGoles = resultadosDAO.partidosMasGoles();

        // Convertir el modelo de tabla a una cadena de texto legible
        StringBuilder cadenaMasGoles = new StringBuilder();
        cadenaMasGoles.append("<html><div style='text-align: center; font-size: 10pt;'>");
        cadenaMasGoles.append("<span style='font-size: 15;'>Partidos con más goles</span><br>");

        if (MasGoles.getRowCount() > 0) {
            cadenaMasGoles.append("<table>");

            // Agregar las columnas
            cadenaMasGoles.append("<tr>");
            for (int i = 0; i < MasGoles.getColumnCount(); i++) {
                cadenaMasGoles.append("<th>").append(MasGoles.getColumnName(i)).append("</th>");
            }
            cadenaMasGoles.append("</tr>");

            // Agregar las filas
            for (int i = 0; i < MasGoles.getRowCount(); i++) {
                cadenaMasGoles.append("<tr>");
                for (int j = 0; j < MasGoles.getColumnCount(); j++) {
                    cadenaMasGoles.append("<td>").append(MasGoles.getValueAt(i, j)).append("</td>");
                }
                cadenaMasGoles.append("</tr>");
            }

            cadenaMasGoles.append("</table>");
        } else {
            cadenaMasGoles.append("No se encontraron resultados.");
        }
        cadenaMasGoles.append("</div></html>");

        // Mostrar el resultado en el JLabel
        String textoLabelMas = cadenaMasGoles.toString();
        lblPanelMasGoles.setText(textoLabelMas);

        JPanel panelMenosGoles = new JPanel();
        panelMenosGoles.setBackground(Color.CYAN);
        panelMenosGoles.setPreferredSize(new Dimension(300, 100));
        JLabel lblPanelMenosGoles = new JLabel();
        panelMenosGoles.add(lblPanelMenosGoles);

        // Obtener el modelo de tabla con los partidos con menos goles
        DefaultTableModel MenosGoles = resultadosDAO.partidosMenosGoles();

        // Convertir el modelo de tabla a una cadena de texto legible
        StringBuilder cadenaMenosGoles = new StringBuilder();
        cadenaMenosGoles.append("<html><div style='text-align: center; font-size: 10pt;'>");
        cadenaMenosGoles.append("<span style='font-size: 15;'>Partidos con menos goles</span><br>");

        if (MenosGoles.getRowCount() > 0) {
            cadenaMenosGoles.append("<table>");

            // Agregar las columnas
            cadenaMenosGoles.append("<tr>");
            for (int i = 0; i < MenosGoles.getColumnCount(); i++) {
                cadenaMenosGoles.append("<th>").append(MenosGoles.getColumnName(i)).append("</th>");
            }
            cadenaMenosGoles.append("</tr>");

            // Agregar las filas
            for (int i = 0; i < MenosGoles.getRowCount(); i++) {
                cadenaMenosGoles.append("<tr>");
                for (int j = 0; j < MenosGoles.getColumnCount(); j++) {
                    cadenaMenosGoles.append("<td>").append(MenosGoles.getValueAt(i, j)).append("</td>");
                }
                cadenaMenosGoles.append("</tr>");
            }

            cadenaMenosGoles.append("</table>");
        } else {
            cadenaMenosGoles.append("No se encontraron resultados.");
        }
        cadenaMenosGoles.append("</div></html>");

        // Mostrar el resultado en el JLabel
        String textoLabelMenos = cadenaMenosGoles.toString();
        lblPanelMenosGoles.setText(textoLabelMenos);

        JPanel panelGanadosEmpatados = new JPanel();
        panelGanadosEmpatados.setBackground(Color.CYAN);
        panelGanadosEmpatados.setPreferredSize(new Dimension(500, 50));
        JLabel lblPanelGanadosEmpatados = new JLabel();

        int[] resultados = resultadosDAO.partidoGanadosEmpatados();
        String ganados = String.valueOf(resultados[0]);
        String empatados = String.valueOf(resultados[1]);
        lblPanelGanadosEmpatados.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + ganados + " - " + empatados + "</span><br>Partidos Ganados y Empatados</div></html>");
        panelGanadosEmpatados.add(lblPanelGanadosEmpatados);

        JPanel panelSeleccionMasGoles = new JPanel();
        panelSeleccionMasGoles.setBackground(Color.CYAN);
        panelSeleccionMasGoles.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelSeleccionMasGoles = new JLabel();

        String[] seleMasGoles = resultadosDAO.seleccionesMasGoles();
        String masGoles = String.valueOf(seleMasGoles[0]);
        lblPanelSeleccionMasGoles.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + masGoles + "</span><br>Selección con más goles</div></html>");
        panelSeleccionMasGoles.add(lblPanelSeleccionMasGoles);

        JPanel panelSeleccionMenosGoles = new JPanel();
        panelSeleccionMenosGoles.setBackground(Color.CYAN);
        panelSeleccionMenosGoles.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelSeleccionMenosGoles = new JLabel();

        String[] seleMenosGoles = resultadosDAO.seleccionesMenosGoles();
        String menosGoles = String.valueOf(seleMenosGoles[0]);
        lblPanelSeleccionMenosGoles.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + menosGoles + "</span><br>Selección con menos goles</div></html>");
        panelSeleccionMenosGoles.add(lblPanelSeleccionMenosGoles);

        JPanel panelSeleccionMasPuntuacion = new JPanel();
        panelSeleccionMasPuntuacion.setBackground(Color.CYAN);
        panelSeleccionMasPuntuacion.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelSeleccionMasPuntuacion = new JLabel();

        String[] seleMasPuntuacion = resultadosDAO.seleccionesMayorPuntuacion();
        String masPuntuacion = String.valueOf(seleMasPuntuacion[0]);
        lblPanelSeleccionMasPuntuacion.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + masPuntuacion + "</span><br>Selección con mayor puntuación</div></html>");
        panelSeleccionMasPuntuacion.add(lblPanelSeleccionMasPuntuacion);

        JPanel panelSeleccionMenorPuntuacion = new JPanel();
        panelSeleccionMenorPuntuacion.setBackground(Color.CYAN);
        panelSeleccionMenorPuntuacion.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelSeleccionMenorPuntuacion = new JLabel();

        String[] seleMenorPuntuacion = resultadosDAO.seleccionesMenorPuntuacion();
        String menorPuntuacion = String.valueOf(seleMenorPuntuacion[6]);
        lblPanelSeleccionMenorPuntuacion.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + menorPuntuacion + "</span><br>Selección con menor puntuación</div></html>");
        panelSeleccionMenorPuntuacion.add(lblPanelSeleccionMenorPuntuacion);

        JPanel panelContinenteMasPuntuacion = new JPanel();
        panelContinenteMasPuntuacion.setBackground(Color.CYAN);
        panelContinenteMasPuntuacion.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelContinenteMasPuntuacion = new JLabel();

        String[] contiMasPuntuacion = resultadosDAO.continentesMayorPuntuacion();
        String ContiMasPuntuacion = String.valueOf(contiMasPuntuacion[0]);
        lblPanelContinenteMasPuntuacion.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + ContiMasPuntuacion + "</span><br>Continente con mayor puntuación</div></html>");
        panelContinenteMasPuntuacion.add(lblPanelContinenteMasPuntuacion);

        JPanel panelContinenteMenorPuntuacion = new JPanel();
        panelContinenteMenorPuntuacion.setBackground(Color.CYAN);
        panelContinenteMenorPuntuacion.setPreferredSize(new Dimension(300, 50));
        JLabel lblPanelContinenteMenorPuntuacion = new JLabel();

        String[] contiMenorPuntuacion = resultadosDAO.continentesMenorPuntuacion();
        String ContiMenorPuntuacion = String.valueOf(contiMenorPuntuacion[0]);
        lblPanelContinenteMenorPuntuacion.setText("<html><div style='text-align: center; font-size: 8pt;'><span style='font-size: 20;'>" + ContiMenorPuntuacion + "</span><br>Continente con menor puntuación</div></html>");
        panelContinenteMenorPuntuacion.add(lblPanelContinenteMenorPuntuacion);


        /*a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Número de partidos cargados \n"
                + "2. Promedio de goles por partido \n"
                + "3. Partido con más goles y partido con menos goles \n"
                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
                + "5. Selcción o selecciones con más goles y con menos goles \n"
                + "6. Selección con más puntos y menos puntos \n"
                + "7. Continente o continentes con más goles y menos goles \n"
                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
         */
        resultadosDAO.totalPartidos();
        jPanelMain.removeAll();
        //  jPanelMain.add(a);
        jPanelMain.add(panelT);
        jPanelMain.add(panelPROM);
        jPanelMain.add(panelMasGoles);
        jPanelMain.add(panelMenosGoles);
        jPanelMain.add(panelGanadosEmpatados);
        jPanelMain.add(panelSeleccionMasGoles);
        jPanelMain.add(panelSeleccionMenosGoles);
        jPanelMain.add(panelSeleccionMasPuntuacion);
        jPanelMain.add(panelSeleccionMenorPuntuacion);
        jPanelMain.add(panelContinenteMasPuntuacion);
        jPanelMain.add(panelContinenteMenorPuntuacion);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte izquierda de la interfaz, dónde se visulaiza el
     * menú de navegaación
     */
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.totalSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.SeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        // Agregar ActionListener al botón "buscar"
        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                List<Seleccion> seleccionesBusqueda = seleccionDAO.SeleccionesBusqueda(field.getText());
                /* List<Resultados>resultadosBusqueda=resultadosDAO.getResultadosBusqueda(field.getText());*/

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"

                // Obtener la lista de selecciones que coinciden con la búsqueda
                field.setText("");
                List<Seleccion> seleccionesBusqueda = seleccionDAO.SeleccionesBusqueda("");
                /* List<Resultados>resultadosBusqueda=resultadosDAO.getResultadosBusqueda(field.getText());*/

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Nombre");
                modeloTabla.addColumn("Continente");
                modeloTabla.addColumn("DT");
                modeloTabla.addColumn("Nacionalidad");

// Agregar las filas correspondientes a la lista de selecciones
                for (Seleccion seleccion : seleccionesBusqueda) {
                    modeloTabla.addRow(new Object[]{seleccion.getNombre(), seleccion.getContinente(), seleccion.getDt(), seleccion.getNacionalidad()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);

                System.out.println("Se ha hecho clic en el botón 'buscar'");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Resultados resultados = new Resultados(columns[1], columns[2], columns[3], columns[4], columns[5], columns[6], columns[7]);
                if (resultadosDAO.registrarResultados(resultados)) {
                    System.out.println("Resultado del grupo  " + resultados.getGrupo() + " registrado");
                } else {
                    System.out.println("Error " + resultados.getGrupo());
                }
            }
            resultados = resultadosDAO.getResultadosMatriz();
            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        buscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Código a ejecutar cuando se detecte el evento de clic en el botón "buscar"
                // Obtener la lista de selecciones que coinciden con la búsqueda
                //  List<Seleccion> seleccionesBusqueda = seleccionDAO.getSeleccionesBusqueda(field.getText());
                List<Resultados> resultadosBusqueda = resultadosDAO.getResultadosBusqueda(field.getText());

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinenteL(), resultado.getContinenteV(), resultado.getGolesL(), resultado.getGolesV()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        limpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                field.setText("");
                List<Resultados> resultadosBusqueda = resultadosDAO.getResultadosBusqueda("");

                // Crear un nuevo modelo de tabla
                DefaultTableModel modeloTabla = new DefaultTableModel();
                modeloTabla.addColumn("Grupo");
                modeloTabla.addColumn("Local");
                modeloTabla.addColumn("Visitante");
                modeloTabla.addColumn("ContinenteL");
                modeloTabla.addColumn("ContinenteV");
                modeloTabla.addColumn("golesL");
                modeloTabla.addColumn("GolesV");

// Agregar las filas correspondientes a la lista de selecciones
                for (Resultados resultado : resultadosBusqueda) {
                    modeloTabla.addRow(new Object[]{resultado.getGrupo(), resultado.getLocal(), resultado.getVisitante(), resultado.getContinenteL(), resultado.getContinenteV(), resultado.getGolesL(), resultado.getGolesV()});
                }

// Establecer el nuevo modelo de tabla en la JTable
                table.setModel(modeloTabla);
                System.out.println("Se ha hecho clic en el botón 'buscar' de resultados");
                // Aquí puedes llamar al método que realiza la búsqueda con el texto ingresado por el usuario en el campo correspondiente
            }
        });

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
    private void pintarPanelDerecho() {

        // Define las dimensiones del panel
        jPanelMain.setPreferredSize((new java.awt.Dimension(620, 420)));
        jPanelMain.setMaximumSize(jPanelLabelTop.getPreferredSize());

        getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
        jPanelRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        jPanelRight.add(jPanelLabelTop, BorderLayout.LINE_START);
        jPanelRight.add(jPanelMain);
        jPanelRight.setPreferredSize((new java.awt.Dimension(620, 540)));
        jPanelRight.setMaximumSize(jPanelRight.getPreferredSize());
    }

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
    private void pintarLabelTop() {
        jLabelTop = new JLabel();
        jLabelTop.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        jLabelTop.setForeground(new java.awt.Color(241, 241, 241));
        jLabelTop.setText("Home");

        JLabel vacioTopLabel = new JLabel();
        jPanelLabelTop.setLayout(new BorderLayout(15, 0));
        jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
        jPanelLabelTop.setBackground(new java.awt.Color(18, 119, 217));
        jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
        jPanelLabelTop.setPreferredSize((new java.awt.Dimension(620, 120)));
        jPanelLabelTop.setMaximumSize(jPanelLabelTop.getPreferredSize());
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
