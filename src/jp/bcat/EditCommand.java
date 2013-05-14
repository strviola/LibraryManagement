package jp.bcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class EditCommand implements CLICommand {
	private BookCatalog catalog;
	
	public EditCommand(BookCatalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public void process(BufferedReader in, PrintWriter out) throws IOException {
		out.print("�ҏW����}��ID: ");
		out.flush();
		Book bookToEdit = catalog.getBook(in.readLine());
		if (bookToEdit == null) {
			out.println("�Y������}��������܂���B");
			return;
		}
		new BookWriter(out, "- ").write(bookToEdit);
		out.println("�ǂ̏���ҏW���邩�I�����Ă��������B");
		out.println("[1]" + "�^�C�g��");
		out.println("[2]" + "����");
		out.println("[3]" + "���");
		out.println("[4]" + "�o�Ŏ�");
		out.println("[5]" + "�o�ŔN����");
		out.println("[6]" + "ISBN/ISSN");
		out.println("[7]" + "���l");
		out.println("[8]" + "�L�[���[�h");
		out.println("[9]" + "�o�^��");
		out.println("[0]" + "(����̒��f)");
		// information to edit
		int type;
		while (true) {
			out.print(": ");
			out.flush();
			try {
				type = Integer.parseInt(in.readLine());
				if (type >= 1 && type <= 9) break;
				else if (type == 0) {
					out.println("�L�����Z������܂����B");
					return;
				} else {
					out.println("������������͂��Ă��������B");
				}
			} catch (NumberFormatException e) {
				out.println("������������͂��Ă��������B");
			}
		}
		out.println("�ǂ̂悤�ɕҏW���邩���͂��Ă�������: ");
		out.println("(�uexit�v�Œ��f���܂�)");
		String value = in.readLine();
		if (value.equals("exit")) {
			out.println("�L�����Z������܂����B");
			return;
		}
		catalog.editBook(bookToEdit, type, value);
		new BookWriter(out, "+ ").write(bookToEdit);
		out.println("���̓��e�œo�^����܂����B");
	}
	
	public static void main(String args[]) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(
					new OutputStreamWriter(System.out), true);
			BookCatalog catalog = new BookCatalog();
			CLICommand command = new EditCommand(catalog);
			command.process(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
