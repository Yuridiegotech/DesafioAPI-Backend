package pojo;
import pojo.Usuario;
public class BodyBaseTests {

    public static Usuario AtualizaUsuario() {
        Usuario AtualizaUsuario = new Usuario();
        AtualizaUsuario.setNome("Diego Yuri Ribeiro Pereira Lima");
        AtualizaUsuario.setCpf("12345678911");
        AtualizaUsuario.setEmail("yuridiego17@email.com");
        AtualizaUsuario.setValor(3000);
        AtualizaUsuario.setParcelas(10);
        AtualizaUsuario.setSeguro(true);
        return AtualizaUsuario;

    }
    public static Usuario CriaUsuarioValido() {
        Usuario CriaUsuarioValido = new Usuario();
        CriaUsuarioValido.setNome("Diego Yuri Ribeiro Pereira Lima");
        CriaUsuarioValido.setCpf("12345678912");
        CriaUsuarioValido.setEmail("yuridiego7@email.com");
        CriaUsuarioValido.setValor(1500);
        CriaUsuarioValido.setParcelas(15);
        CriaUsuarioValido.setSeguro(true);
        return CriaUsuarioValido;

    }

    public static Usuario CriaUsuarioCpfRepetido() {
        Usuario CriaUsuarioCpfRepetido = new Usuario();
        CriaUsuarioCpfRepetido.setNome("Diego Yuri Ribeiro Pereira Lima");
        CriaUsuarioCpfRepetido.setCpf("17822386034"); //CPF Já existente na base
        CriaUsuarioCpfRepetido.setEmail("yuridiego7@email.com");
        CriaUsuarioCpfRepetido.setValor(1500);
        CriaUsuarioCpfRepetido.setParcelas(15);
        CriaUsuarioCpfRepetido.setSeguro(true);
        return CriaUsuarioCpfRepetido;

    }
    public static Usuario CriaUsuarioValorMenor() {
        Usuario CriaUsuarioValorMenor = new Usuario();
        CriaUsuarioValorMenor.setNome("Diego Yuri Ribeiro Pereira Lima");
        CriaUsuarioValorMenor.setCpf("12345678914");
        CriaUsuarioValorMenor.setEmail("yuridiego7@email.com");
        CriaUsuarioValorMenor.setValor(900);
        CriaUsuarioValorMenor.setParcelas(15);
        CriaUsuarioValorMenor.setSeguro(true);
        return CriaUsuarioValorMenor;

    }
    public static Usuario CriaUsuarioValorMaior() {
        Usuario CriaUsuarioValorMaior = new Usuario();
        CriaUsuarioValorMaior.setNome("Diego Yuri Ribeiro Pereira Lima");
        CriaUsuarioValorMaior.setCpf("12345678915");
        CriaUsuarioValorMaior.setEmail("yuridiego7@email.com");
        CriaUsuarioValorMaior.setValor(45000);
        CriaUsuarioValorMaior.setParcelas(15);
        CriaUsuarioValorMaior.setSeguro(true);
        return CriaUsuarioValorMaior;

    }
    public static Usuario CriaUsuarioParcelaMenor() {
        Usuario CriaUsuarioParcelaMenor = new Usuario();
        CriaUsuarioParcelaMenor.setNome("Diego Yuri Ribeiro Pereira Lima");
        CriaUsuarioParcelaMenor.setCpf("12345678916");
        CriaUsuarioParcelaMenor.setEmail("yuridiego7@email.com");
        CriaUsuarioParcelaMenor.setValor(1500);
        CriaUsuarioParcelaMenor.setParcelas(1);
        CriaUsuarioParcelaMenor.setSeguro(true);
        return CriaUsuarioParcelaMenor;

    }
    public static Usuario CriaUsuarioParcelaMaior() {
        Usuario CriaUsuarioarcelaMaior = new Usuario();
        CriaUsuarioarcelaMaior.setNome("Diego Yuri Ribeiro Pereira Lima");
        CriaUsuarioarcelaMaior.setCpf("12345678917");
        CriaUsuarioarcelaMaior.setEmail("yuridiego7@email.com");
        CriaUsuarioarcelaMaior.setValor(1500);
        CriaUsuarioarcelaMaior.setParcelas(50);
        CriaUsuarioarcelaMaior.setSeguro(true);
        return CriaUsuarioarcelaMaior;

    }
}
