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
		Book bookToReturn = catalog.getBook(in.readLine());
		if (bookToReturn == null) {
			out.println("�Y������}��������܂���B");
			return;
		}
		new BookWriter(out, "- ").write(bookToReturn);
		if (! bookToReturn.getStatus()) {
			out.println("���̐}���݂͑��o�����ł͂���܂���B");
			return;
		}
		out.print("���̐}����ԋp���܂��B��낵���ł���? [y/n]");
		out.flush();
		if (! "y".equals(in.readLine())) {
			out.println("�L�����Z������܂����B");
			return;
		}
		catalog.flipBookStatus(bookToReturn);
		out.println("�ԋp���������܂����B");
	}

}
