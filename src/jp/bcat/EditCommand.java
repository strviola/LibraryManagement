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
		out.print("編集する図書ID: ");
		out.flush();
		Book bookToEdit = catalog.getBook(in.readLine());
		if (bookToEdit == null) {
			out.println("該当する図書がありません。");
			return;
		}
		new BookWriter(out, "- ").write(bookToEdit);
		out.println("どの情報を編集するか選択してください。");
		out.println("[1]" + "タイトル");
		out.println("[2]" + "著者");
		out.println("[3]" + "訳者");
		out.println("[4]" + "出版社");
		out.println("[5]" + "出版年月日");
		out.println("[6]" + "ISBN/ISSN");
		out.println("[7]" + "備考");
		out.println("[8]" + "キーワード");
		out.println("[9]" + "登録者");
		out.println("[0]" + "(操作の中断)");
		// information to edit
		int type;
		while (true) {
			out.print(": ");
			out.flush();
			try {
				type = Integer.parseInt(in.readLine());
				if (type >= 1 && type <= 9) break;
				else if (type == 0) {
					out.println("キャンセルされました。");
					return;
				} else {
					out.println("正しい情報を入力してください。");
				}
			} catch (NumberFormatException e) {
				out.println("正しい情報を入力してください。");
			}
		}
		out.println("どのように編集するか入力してください: ");
		out.println("(「exit」で中断します)");
		String value = in.readLine();
		if (value.equals("exit")) {
			out.println("キャンセルされました。");
			return;
		}
		catalog.editBook(bookToEdit, type, value);
		new BookWriter(out, "+ ").write(bookToEdit);
		out.println("この内容で登録されました。");
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
