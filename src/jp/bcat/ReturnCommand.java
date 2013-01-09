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
		out.print("返却したい図書ID: ");
		out.flush();
		Book bookToLend = catalog.getBook(in.readLine());
		if (bookToLend == null) {
			out.println("該当する図書がありません。");
			return;
		}
		new BookWriter(out, "- ").write(bookToLend);
		if (! bookToLend.getStatus()) {
			out.println("その図書は貸し出し中ではありません。");
			return;
		}
		out.print("この図書を返却します。よろしいですか? [y/n]");
		if (! "y".equals(in.readLine())) {
			out.println("キャンセルされました。");
			return;
		}
		bookToLend.setStatus(false);
		out.println("返却が完了しました。");
	}

}
