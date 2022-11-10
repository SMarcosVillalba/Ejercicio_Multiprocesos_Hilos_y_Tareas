import java.util.Random;

public class Examinador implements Runnable {
    private Thread hilo;
    BufferExamenes buffer;

    public Thread getHilo() {
        return hilo;
    }

    public Examinador(String alumno, BufferExamenes generador) {

        // Construye el hilo. El nombre será el nombre del alumno.

        this.hilo = new Thread(this, alumno);

        // Establece el valor de la propiedad buffer

         this.buffer= generador;

        // Inicia el hilo.
        this.hilo.start();

    }

    @Override
    public void run() {
        String codigoExamen = this.buffer.consumirExamen();


        String []respuestas ={"A","B","C","D","Sin contestar"};
        if (codigoExamen != null) {
            // Simula aquí un examen de 10 preguntas cuyas respuestas se seleccionarán al azar entre A, B, C, D o – (sin contestar).

            for(int i =0; i<10;i++){


                 int aleatorio = (int) (Math.random()*5);
                 System.out.println(codigoExamen+";" + this.hilo.getName()+";Pregunta "+i+";"+ respuestas[aleatorio]);

            }
        }
        else {
            System.out.println("Agotado tiempo de espera y " +  "no hay más exámenes");
        }
    }
}

