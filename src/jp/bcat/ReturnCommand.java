package jp.bcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		Book bookToReturn = catalog.getBook(in.readLine());
		if (bookToReturn == null) {
			out.println("該当する図書がありません。");
			return;
		}
		new BookWriter(out, "- ").write(bookToReturn);
		if (! bookToReturn.getStatus()) {
			out.println("その図書は貸し出し中ではありません。");
			return;
		}
		out.print("この図書を返却します。よろしいですか? [y/n]");
		out.flush();
		if (! "y".equals(in.readLine())) {
			out.println("キャンセルされました。");
			return;
		}
		catalog.flipBookStatus(bookToReturn);
		out.println("返却が完了しました。");
	}

	public static void main(String args[]) {
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(
					new OutputStreamWriter(System.out), true);
			BookCatalog catalog = new BookCatalog();
			CLICommand command = new ReturnCommand(catalog);
			command.process(in, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
