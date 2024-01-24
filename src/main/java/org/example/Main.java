package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        variables con los datos de conexion para la base de datos
        String urlConexion = "jdbc:postgresql://localhost:5432/Banco";
        String usuario = "postgres";
        String password = "postgres";

        Scanner scanner=new Scanner(System.in);

        String sentenciaSQL = "Select * FROM accounts";

//        conexion a la base de datos
        try (Connection con = DriverManager.getConnection(urlConexion, usuario, password);
             PreparedStatement sentencia= con.prepareStatement(sentenciaSQL)){
            ResultSet resultados = sentencia.executeQuery();

/*            while (resultados.next()){

                    Integer accountid = resultados.getInt("accountid");
                    String iban = resultados.getString("iban");
                    String balance = resultados.getString("balance");
                    String clientid = resultados.getString("clientid");

                    System.out.println("accountid: "+accountid+" iban: "+iban+" balance: "+balance+" clientid: "+clientid);
            }*/

            crearCliente(con, scanner);

            crearCuenta(con,scanner);

            int id1 = 0;
            borrarcuentaid(con,id1);

            String iban1 = "0";
            borrarcuentaiban(con,iban1);

            int id2=0;
            borrarClienteid(con,id2);

            Cliente cliente1 = new Cliente(9,"Paco");
            borrarClienteCliente(con,cliente1);

            int id3= 0;
            borrarCuentasCliente(con,id3);

            Cliente cliente2 = new Cliente(10,"Fran");
            updateCliente(con,cliente2);

            Cuenta_bancaria cuenta1 = new Cuenta_bancaria(12,"1245678p",8743,9);
            updateCuenta(con,cuenta1);

            listarCuentas(con);

            listarClientes(con);

            listarCuenta(con, scanner);

            listarCliente(con,scanner);
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    //crear cuenta/cliente
    public static void crearCliente(Connection connection, Scanner scanner) throws SQLException {
        String insertDataSQL = "INSERT INTO clientes (id_cliente,nombre,apellido,telefono,usuario,contraseña,dni,email,nacionalidad,fecha_nacimiento,calle,cp,municipio,provincia)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?),";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setInt(1, scanner.nextInt());
            preparedStatement.setString(2, scanner.nextLine());
            preparedStatement.setString(3, scanner.nextLine());
            preparedStatement.setInt(4, scanner.nextInt());
            preparedStatement.setString(5, scanner.nextLine());
            preparedStatement.setString(6, scanner.nextLine());
            preparedStatement.setString(7, scanner.nextLine());
            preparedStatement.setString(8, scanner.nextLine());
            preparedStatement.setString(9, scanner.nextLine());
            preparedStatement.setString(10, scanner.nextLine());
            preparedStatement.setString(11, scanner.nextLine());
            preparedStatement.setInt(12, scanner.nextInt());
            preparedStatement.setString(13, scanner.nextLine());
            preparedStatement.setString(14, scanner.nextLine());
            preparedStatement.executeUpdate();
            System.out.println("Datos insertados exitosamente");
        }catch (SQLException e){

        }
    }

    public static void crearCuenta(Connection connection, Scanner scanner) throws SQLException {
        String insertDataSQL = "INSERT INTO accounts (accountid,iban,balance,clientid)\n" +
                "VALUES (?,?,?,?),";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL)) {
            preparedStatement.setInt(1, scanner.nextInt());
            preparedStatement.setString(2, scanner.nextLine());
            preparedStatement.setDouble(3, scanner.nextDouble());
            preparedStatement.setInt(4, scanner.nextInt());
            preparedStatement.executeUpdate();
            System.out.println("Datos insertados exitosamente");
        }catch (SQLException e){

        }
    }

/*    public static void selectData(Connection connection) throws SQLException {
        String selectDataSQL = "SELECT * FROM ejemplo";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectDataSQL)) {
            System.out.println("Datos recuperados de la tabla:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
        }
    }*/
    // borrar cuenta dado su iban y borrar cuenta dado su id
    public static void borrarcuentaid(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM accounts WHERE accountid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Cuenta eliminada exitosamente");
        }catch (SQLException e){

        }
    }

    public static void borrarcuentaiban(Connection connection, String iban) throws SQLException {
        String deleteDataSQL = "DELETE FROM accounts WHERE iban = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setString(1, iban);
            preparedStatement.executeUpdate();
            System.out.println("Cuenta eliminada exitosamente");
        }catch (SQLException e){

        }
    }

    // borrar cliente pasándole el objeto y pasándole su id
    public static void borrarClienteid(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM clientes WHERE id_cliente = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Cliente eliminada exitosamente");
        }catch (SQLException e){

        }
    }

    public static void borrarClienteCliente(Connection connection, Cliente cliente) throws SQLException {
        String deleteDataSQL = "DELETE FROM clientes WHERE id_cliente = "+cliente.getId_cliente();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setInt(1, cliente.getId_cliente());
            preparedStatement.executeUpdate();
            System.out.println("Cliente eliminada exitosamente");
        }catch (SQLException e){

        }
    }

    // borrar todas las cuentas de un cliente
    public static void borrarCuentasCliente(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM accounts WHERE clientid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteDataSQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Cuentas eliminadas exitosamente");
        }catch (SQLException e){

        }
    }

    // actualizar cuenta/cliente
    public static void updateCliente(Connection connection, Cliente cliente) throws SQLException {
        String updateDataSQL = "UPDATE clientes SET nombre=?,apellido=?,telefono=?,usuario=?,contraseña=?,dni=?,email=?,nacionalidad=?,fecha_nacimiento=?,calle=?,cp=?,municipio=?,provincia=? WHERE id_cliente = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateDataSQL)) {
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setInt(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getUsuario());
            preparedStatement.setString(5, cliente.getContrasenya());
            preparedStatement.setString(6, cliente.getDni());
            preparedStatement.setString(7, cliente.getEmail());
            preparedStatement.setString(8, cliente.getNacionalidad());
            preparedStatement.setString(9, cliente.getFecha_nacimiento());
            preparedStatement.setString(10, cliente.getCalle());
            preparedStatement.setInt(11, cliente.getCp());
            preparedStatement.setString(12, cliente.getMunicipio());
            preparedStatement.setString(13, cliente.getProvincia());
            preparedStatement.setInt(14, cliente.getId_cliente());
            preparedStatement.executeUpdate();
            System.out.println("Datos actualizados exitosamente");
        }catch (SQLException e){

        }
    }

    public static void updateCuenta(Connection connection, Cuenta_bancaria cuenta) throws SQLException {
        String updateDataSQL = "UPDATE accounts SET iban=?,balance=?,clientid=? WHERE accountid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateDataSQL)) {
            preparedStatement.setString(1, cuenta.getIban());
            preparedStatement.setDouble(2, cuenta.getBalance());
            preparedStatement.setInt(3, cuenta.getId_cliente());
            preparedStatement.setInt(4, cuenta.getId_cuenta());
            preparedStatement.executeUpdate();
            System.out.println("Datos actualizados exitosamente");
        }catch (SQLException e){

        }
    }

    // listar todas las cuentas/clientes y su información
    public static void listarCuentas(Connection connection) throws SQLException {
        String selectDataSQL = "SELECT * FROM accounts";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectDataSQL)) {
            System.out.println("Datos de la tabla accounts:");
            while (resultSet.next()){

                    Integer accountid = resultSet.getInt("accountid");
                    String iban = resultSet.getString("iban");
                    double balance = resultSet.getDouble("balance");
                    Integer clientid = resultSet.getInt("clientid");

                    System.out.println("accountid: "+accountid+" iban: "+iban+" balance: "+balance+" clientid: "+clientid);
            }
        }catch (SQLException e){

        }
    }

    public static void listarClientes(Connection connection) throws SQLException {
        String selectDataSQL = "SELECT * FROM clientes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectDataSQL)) {
            System.out.println("Datos de la tabla clientes:");
            while (resultSet.next()){

                Integer id_cliente = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Integer telefono = resultSet.getInt("telefono");
                String usuario = resultSet.getString("usuario");
                String contraseña = resultSet.getString("contraseña");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                String nacionalidad = resultSet.getString("nacionalidad");
                String fecha_nacimiento = resultSet.getString("fecha_nacimiento");
                String calle = resultSet.getString("calle");
                Integer cp = resultSet.getInt("cp");
                String municipio = resultSet.getString("municipio");
                String provincia = resultSet.getString("provincia");

                System.out.println("id_cliente: "+id_cliente+" nombre: "+nombre+" apellido: "+apellido+" telefono: "+telefono+" usuario: "+usuario+" contraseña: "+contraseña+" dni: "+dni+" email: "+email+" nacionalidad: "+nacionalidad+" fecha de nacimiento: "+fecha_nacimiento+" calle: "+calle+" cp: "+cp+" municipio: "+municipio+" provincia: "+provincia);
            }
        }catch (SQLException e){

        }
    }

    // mostrar información de una cuenta/cliente
    public static void listarCuenta(Connection connection, Scanner scanner) throws SQLException {
        String selectDataSQL = "SELECT * FROM accounts WHERE accountid = ?";
        System.out.println("Introduce el accountid: ");
        try (PreparedStatement sentencia= connection.prepareStatement(selectDataSQL)){
            sentencia.setInt(1, scanner.nextInt());
            ResultSet resultados = sentencia.executeQuery();

            while (resultados.next()){

                    Integer accountid = resultados.getInt("accountid");
                    String iban = resultados.getString("iban");
                    String balance = resultados.getString("balance");
                    String clientid = resultados.getString("clientid");

                    System.out.println("accountid: "+accountid+" iban: "+iban+" balance: "+balance+" clientid: "+clientid);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
    }

    public static void listarCliente(Connection connection, Scanner scanner) throws SQLException {
        String selectDataSQL = "SELECT * FROM clientes WHERE id_cliente = ?";
        System.out.println("Introduce el accountid: ");
        try (PreparedStatement sentencia= connection.prepareStatement(selectDataSQL)){
            sentencia.setInt(1, scanner.nextInt());
            ResultSet resultSet = sentencia.executeQuery();

            while (resultSet.next()) {

                Integer id_cliente = resultSet.getInt("id_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Integer telefono = resultSet.getInt("telefono");
                String usuario = resultSet.getString("usuario");
                String contraseña = resultSet.getString("contraseña");
                String dni = resultSet.getString("dni");
                String email = resultSet.getString("email");
                String nacionalidad = resultSet.getString("nacionalidad");
                String fecha_nacimiento = resultSet.getString("fecha_nacimiento");
                String calle = resultSet.getString("calle");
                Integer cp = resultSet.getInt("cp");
                String municipio = resultSet.getString("municipio");
                String provincia = resultSet.getString("provincia");

                System.out.println("id_cliente: " + id_cliente + " nombre: " + nombre + " apellido: " + apellido + " telefono: " + telefono + " usuario: " + usuario + " contraseña: " + contraseña + " dni: " + dni + " email: " + email + " nacionalidad: " + nacionalidad + " fecha de nacimiento: " + fecha_nacimiento + " calle: " + calle + " cp: " + cp + " municipio: " + municipio + " provincia: " + provincia);
            }
        }catch (SQLException e){

        }
    }

    // Transaccion
    public static void transaction(Connection connection, Cuenta_bancaria cuenta_origen, Cuenta_bancaria cuenta_destino, int cantidad) {
        try {
            connection.setAutoCommit(false); // Desactivar la confirmación automática

            // Operaciones dentro de la transacción
            transaccionOrigen(connection,cuenta_origen,cantidad);
            transaccionDestino(connection,cuenta_destino,cantidad);

            connection.commit(); // Confirmar la transacción
            System.out.println("Transacción completada exitosamente");
        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void transaccionOrigen(Connection connection, Cuenta_bancaria cuenta_destino, int cantidad)  {
        String updateDataSQL = "UPDATE accounts SET iban=?,balance=?,clientid=? WHERE accountid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateDataSQL)) {
            preparedStatement.setString(1, cuenta_destino.getIban());
            preparedStatement.setDouble(2, cuenta_destino.getBalance()-cantidad);
            preparedStatement.setInt(3, cuenta_destino.getId_cliente());
            preparedStatement.setInt(4, cuenta_destino.getId_cuenta());
            preparedStatement.executeUpdate();
            System.out.println("Transaccion origen completada");
        }catch (SQLException e){

        }
    }

    public static void transaccionDestino(Connection connection,Cuenta_bancaria cuenta_destino,int cantidad)  {
        String updateDataSQL = "UPDATE accounts SET iban=?,balance=?,clientid=? WHERE accountid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateDataSQL)) {
            preparedStatement.setString(1, cuenta_destino.getIban());
            preparedStatement.setDouble(2, cuenta_destino.getBalance()+cantidad);
            preparedStatement.setInt(3, cuenta_destino.getId_cliente());
            preparedStatement.setInt(4, cuenta_destino.getId_cuenta());
            preparedStatement.executeUpdate();
            System.out.println("Transaccion destino completada");
        }catch (SQLException e){

        }
    }
}