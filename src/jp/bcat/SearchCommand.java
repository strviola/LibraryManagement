package jp.bcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SearchCommand implements CLICommand {
	protected BookCatalog catalog;
	
	public SearchCommand(BookCatalog catalog) {
		this.catalog = catalog;
	}

	@Override
	public void process(BufferedReader in, PrintWriter out) throws IOException {
		out.print("キーワードを入力してください: ");
		out.flush();
		Book[] result = catalog.searchBooks(in.readLine());	
		if (result.length == 0) {
			out.println("キーワードに一致する蔵書が見つかりませんでした。");
		} else {
			out.println("検索結果: " + result.length + "項目");
			BookWriter writer = new BookWriter(out, " ");
			for (int i = 0; i < result.length; i++) {
				out.println("[" + (i+1) + "]");
				out.println("図書ID: " + result[i].getBookId());
				writer.write(result[i]);
				out.println();
			}
		}
	}
}
