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
		out.print("借りたい図書ID: ");
		out.flush();
		Book bookToLend = catalog.getBook(in.readLine());
		if (bookToLend == null) {
			out.println("該当する図書がありません。");
			return;
		}
		new BookWriter(out, "- ").write(bookToLend);
		if (bookToLend.getStatus()) {
			// 図書が貸し出し中
			out.println("その図書は貸し出し中です。");
			return;
		}
		out.print("この図書を貸し出します。よろしいですか? [y/n]: ");
		out.flush();
		if (! "y".equals(in.readLine())) {
			out.println("キャンセルされました。");
			return;
		}
		catalog.flipBookStatus(bookToLend);
		out.println("貸し出しが完了しました。");
	}

}
