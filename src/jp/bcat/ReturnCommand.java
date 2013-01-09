package jp.bcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ReturnCommand implements CLICommand {
	private BookCatalog catalog;
	
	public ReturnCommand(BookCatalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public void process(BufferedReader in, PrintWriter out) throws IOException {
		out.print("�ԋp�������}��ID: ");
		out.flush();
		Book bookToLend = catalog.getBook(in.readLine());
		if (bookToLend == null) {
			out.println("�Y������}��������܂���B");
			return;
		}
		new BookWriter(out, "- ").write(bookToLend);
		if (! bookToLend.getStatus()) {
			out.println("���̐}���݂͑��o�����ł͂���܂���B");
			return;
		}
		out.print("���̐}����ԋp���܂��B��낵���ł���? [y/n]");
		if (! "y".equals(in.readLine())) {
			out.println("�L�����Z������܂����B");
			return;
		}
		bookToLend.setStatus(false);
		out.println("�ԋp���������܂����B");
	}

}
