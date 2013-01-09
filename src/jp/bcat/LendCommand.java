package jp.bcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LendCommand implements CLICommand {
	private BookCatalog catalog;
	
	public LendCommand(BookCatalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public void process(BufferedReader in, PrintWriter out) throws IOException {
		out.print("�؂肽���}��ID: ");
		out.flush();
		Book bookToLend = catalog.getBook(in.readLine());
		if (bookToLend == null) {
			out.println("�Y������}��������܂���B");
			return;
		}
		new BookWriter(out, "- ").write(bookToLend);
		if (bookToLend.getStatus()) {
			// �}�����݂��o����
			out.println("���̐}���݂͑��o�����ł��B");
			return;
		}
		out.print("���̐}����݂��o���܂��B��낵���ł���? [y/n]: ");
		out.flush();
		if (! "y".equals(in.readLine())) {
			out.println("�L�����Z������܂����B");
			return;
		}
		catalog.flipBookStatus(bookToLend);
		out.println("�݂��o�����������܂����B");
	}

}
