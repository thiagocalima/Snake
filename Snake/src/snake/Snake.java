/* 
 * Paradigmas de Linguagem de Programacao
 * Prof.: Ismar Frango				Turma: 5N
 * 
 * Projeto de Programação
 * 
 * Aluno: Carlos H. F. Ferreira		T.I.A.: 3075662-6
 * Aluno: Murylo S. Juliani			T.I.A.: 3090926-0
 * Aluno: Thiago de C. A. Lima		T.I.A.: 4091993-5
 * 
 */

package snake;

import javax.swing.JFrame;



public class Snake extends JFrame {

    public Snake() {

        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 340);
        setLocationRelativeTo(null);
        setTitle("Projeto de Programacao - Snake");

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Snake();
    }
}