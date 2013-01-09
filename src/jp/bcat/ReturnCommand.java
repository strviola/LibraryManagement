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
		out.print("•Ô‹p‚µ‚½‚¢}‘ID: ");
		out.flush();
		Book bookToReturn = catalog.getBook(in.readLine());
		if (bookToReturn == null) {
			out.println("ŠY“–‚·‚é}‘‚ª‚ ‚è‚Ü‚¹‚ñB");
			return;
		}
		new BookWriter(out, "- ").write(bookToReturn);
		if (! bookToReturn.getStatus()) {
			out.println("‚»‚Ì}‘‚Í‘İ‚µo‚µ’†‚Å‚Í‚ ‚è‚Ü‚¹‚ñB");
			return;
		}
		out.print("‚±‚Ì}‘‚ğ•Ô‹p‚µ‚Ü‚·B‚æ‚ë‚µ‚¢‚Å‚·‚©? [y/n]");
		out.flush();
		if (! "y".equals(in.readLine())) {
			out.println("ƒLƒƒƒ“ƒZƒ‹‚³‚ê‚Ü‚µ‚½B");
			return;
		}
		catalog.flipBookStatus(bookToReturn);
		out.println("•Ô‹p‚ªŠ®—¹‚µ‚Ü‚µ‚½B");
	}

}
