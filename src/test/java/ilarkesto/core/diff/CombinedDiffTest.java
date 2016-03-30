/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package ilarkesto.core.diff;

import static ilarkesto.core.diff.TokenDiff.combinedDiff;
import static ilarkesto.core.time.Tm.getCurrentTimeMillis;
import ilarkesto.testng.ATest;
import org.testng.annotations.Test;

public class CombinedDiffTest extends ATest {

	@Test
	public void test() {
		assertDiff("hello world", "hello happy world", "hello [+happy ]world");
	}

	@Test
	public void combined() {
		assertDiff("first line\nsecond line\nthird line",
			"first head line\nsecond line\ninbetween line\nthird line at the end",
			"first [+head ]line\nsecond line\n[+inbetween line\n]third line[+ at the end]");
	}

	@Test
	public void longText() {
		assertDiff(
			"== Kunagi Developer Guide ==\n"
					+ "\n"
					+ "Kunagi uses Git for version control. You can read up about Git on the [http://www.kernel.org/pub/software/scm/git/docs/user-manual.html Git User's Manual].\n"
					+ "\n"
					+ "=== Setting up the development environment ===\n"
					+ "\n"
					+ "Create a [http://github.com GitHub] account.\n"
					+ "\n"
					+ "On GitHub, create your personal forks of [http://github.com/witek/ilarkesto ilarkesto] and [http://github.com/witek/kunagi kunagi]. Make sure to add a public key to your Git account following the instruction on [http://help.github.com/linux-key-setup/ generating SSH keys] if you don't already have one.\n"
					+ "\n"
					+ "Install Git on your computer:\n"
					+ "\n"
					+ " sudo apt-get install git-gui\n"
					+ "\n"
					+ "Windows users can follow [http://nathanj.github.com/gitguide/tour.html this tutorial]. \n"
					+ "\n"
					+ "Set username and email in git:\n"
					+ "\n"
					+ " git config --global user.name \"Full Name\"\n"
					+ " git config --global user.email \"email@gmail.com\"\n"
					+ "\n"
					+ "Create local clones of ilarkesto and kunagi in your workspace directory:\n"
					+ "\n"
					+ " git clone git@github.com:your-github-name/ilarkesto.git\n"
					+ " git clone git@github.com:your-github-name/kunagi.git\n"
					+ "\n"
					+ "Download dependencies by executing the <code>update-libs.bsh</code> script in the ilarkesto directory.\n"
					+ "\n"
					+ "Install [http://code.google.com/webtoolkit/download.html GWT].\n"
					+ "\n"
					+ "Set the following ''Linked Resources'' in [http://eclipse.org/ Eclipse]:\n"
					+ "\n"
					+ " GWT = /path-to/gwt\n"
					+ " OUTPUT_DIR = /path-to/class-output-dir\n"
					+ "\n"
					+ "Open ilarkesto and kunagi as projects in Eclipse.\n"
					+ "\n"
					+ "Start Kunagi with Eclipse by running <code>ScrumGwtApplication.linux.launch</code> from the kunagi project.\n"
					+ "\n"
					+ "\n"
					+ "=== Collaborating ===\n"
					+ "\n"
					+ "Commit changes to your local git repository:\n"
					+ "\n"
					+ " git add .\n"
					+ " git commit -am \"my commit comment\"\n"
					+ "\n"
					+ "Push changes to your remote repository on GitHub:\n"
					+ "\n"
					+ " git push\n"
					+ "\n"
					+ "Pull changes from your remote repository on GitHub:\n"
					+ "\n"
					+ " git pull\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "=== Architecture Overview ===\n"
					+ "\n"
					+ "Kunagi is a web application, implemented in Java, based on the Google Web Toolkit (GWT). Therefore it is separated into two runtime environments, the Java server and the GWT/JavaScript client.\n"
					+ "\n"
					+ "==== GWT Client ====\n"
					+ "\n"
					+ "The client sources are Java files in <code>src/main/java/scrum/client/...</code>. These are compiled to JavaScript by GWT. The entry point is <code>ScrumGwtApplication</code>. Every single user runs his own instance of the client.\n"
					+ "\n"
					+ "==== Java Server ====\n"
					+ "\n"
					+ "The server source files are placed in <code>src/main/java/scrum/server/...</code>. The entry point is <code>ScrumWebApplication</code>.\n"
					+ "\n"
					+ "==== Client-Server communication ====\n"
					+ "\n"
					+ "The client communicates with the server through asynchronous service calls. Every service call returns the same <code>DataTransferObject</code> to the client, which contains data for the client. It can even contain data, which has nothing to do with the service call. This could be entities, which were modified by other clients.\n"
					+ "\n"
					+ "The server manages all active clients with <code>Conversation</code> objects. These conversations contain the <code>DataTransferObject</code>. New data can be put into the transfer object at any time. It gets transferred to the client with the next service call.\n"
					+ "\n"
					+ "Service calls are implemented in the <code>ScrumServiceImpl</code> class and run on the server.\n"
					+ "\n"
					+ "See '''Model Driven Architechture''' for creating service calls.\n"
					+ "\n"
					+ "==== Entities ====\n"
					+ "\n"
					+ "Entities are persistent objects. For every entity, there are two implementations. One on the server and one on the client. They always have the same class name, only the package differs.\n"
					+ "\n"
					+ "When entity properties are changed on the client, the framework triggers the <code>ChangePropertiesServiceCall</code> immediately. This service call sends the changes to the server, which updates the server instance and sends the changes to all other active conversations/clients.\n"
					+ "\n"
					+ "On the server, on startup, all entities are loaded from XML files into memory. When entity properties are changed, the framework saves the modified entities back to the XML files.\n"
					+ "\n"
					+ "==== Model Driven Architecture ====\n"
					+ "\n"
					+ "Some source files are generated from models. These are entities and its DAOs (Data Access Objects), service calls, events and client components. The generated Java files are checked in to SCM for convinience into <code>src/generated/java/...</code>.\n"
					+ "\n"
					+ "The models are coded in Java in the <code>ScrumModelApplication</code> class or modeled in the GUI modeller <code>ScrumModeller</code> and saved in <code>model.csv</code>. Executing <code>ScrumModeller</code> and clicking <code>Save & Generate</code> generates the sources.",
			"== Kunagi Developer Guide ==\n"
					+ "\n"
					+ "Kunagi uses Git for version control. You can read up about Git on the [http://www.kernel.org/pub/software/scm/git/docs/user-manual.html Git User's Manual].\n"
					+ "\n"
					+ "=== Setting up the development environment ===\n"
					+ "\n"
					+ "Create a [http://github.com GitHub] account.\n"
					+ "\n"
					+ "On GitHub, create your personal forks of [http://github.com/witek/ilarkesto ilarkesto] and [http://github.com/witek/kunagi kunagi]. Make sure to add a public key to your Git account following the instruction on [http://help.github.com/linux-key-setup/ generating SSH keys] if you don't already have one.\n"
					+ "\n"
					+ "Install Git on your computer:\n"
					+ "\n"
					+ " sudo apt-get install git-gui\n"
					+ "\n"
					+ "Windows users can follow [http://nathanj.github.com/gitguide/tour.html this tutorial]. \n"
					+ "\n"
					+ "Set username and email in git:\n"
					+ "\n"
					+ " git config --global user.name \"Full Name\"\n"
					+ " git config --global user.email \"email@gmail.com\"\n"
					+ "\n"
					+ "Create local clones of ilarkesto and kunagi in your workspace directory:\n"
					+ "\n"
					+ " git clone git@github.com:your-github-name/ilarkesto.git\n"
					+ " git clone git@github.com:your-github-name/kunagi.git\n"
					+ "\n"
					+ "Download dependencies by executing the <code>update-libs.bsh</code> script in the ilarkesto directory.\n"
					+ "\n"
					+ "Install [http://code.google.com/webtoolkit/download.html GWT].\n"
					+ "\n"
					+ "Set the following ''Linked Resources'' in [http://eclipse.org/ Eclipse]:\n"
					+ "\n"
					+ " GWT = /path-to/gwt\n"
					+ " OUTPUT_DIR = /path-to/class-output-dir\n"
					+ "\n"
					+ "Open ilarkesto and kunagi as projects in Eclipse.\n"
					+ "\n"
					+ "Start Kunagi with Eclipse by running <code>ScrumGwtApplication.linux.launch</code> from the kunagi project.\n"
					+ "\n"
					+ "\n"
					+ "=== Collaborating ===\n"
					+ "\n"
					+ "Commit changes to your local git repository:\n"
					+ "\n"
					+ " git add .\n"
					+ " git commit -am \"my commit comment\"\n"
					+ "\n"
					+ "Push changes to your remote repository on GitHub:\n"
					+ "\n"
					+ " git push\n"
					+ "\n"
					+ "Pull changes from your remote repository on GitHub:\n"
					+ "\n"
					+ " git pull\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "=== Architecture Overview ===\n"
					+ "\n"
					+ "Kunagi is a web application, implemented in Java, based on the Google Web Toolkit (GWT). Therefore it is separated into two runtime environments, the Java server and the GWT/JavaScript client.\n"
					+ "\n"
					+ "==== GWT Client ====\n"
					+ "\n"
					+ "The client sources are Java files in <code>src/main/java/scrum/client/...</code>. These are compiled to JavaScript by GWT. The entry point is <code>ScrumGwtApplication</code>. Every single user runs his own instance of the client.\n"
					+ "\n"
					+ "==== Java Server ====\n"
					+ "\n"
					+ "The server source files are placed in <code>src/main/java/scrum/server/...</code>. The entry point is <code>ScrumWebApplication</code>.\n"
					+ "\n"
					+ "==== Client-Server communication ====\n"
					+ "\n"
					+ "The client communicates with the server through asynchronous service calls. Every service call returns the same <code>DataTransferObject</code> to the client, which contains data for the client. It can even contain data, which has nothing to do with the service call. This could be entities, which were modified by other clients.\n"
					+ "\n"
					+ "The server manages all active clients with <code>Conversation</code> objects. These conversations contain the <code>DataTransferObject</code>. New data can be put into the transfer object at any time. It gets transferred to the client with the next service call.\n"
					+ "\n"
					+ "Service calls are implemented in the <code>ScrumServiceImpl</code> class and run on the server.\n"
					+ "\n"
					+ "See '''Model Driven Architechture''' for creating service calls.\n"
					+ "\n"
					+ "==== Entities ====\n"
					+ "\n"
					+ "Entities are persistent objects. For every entity, there are two implementations. One on the server and one on the client. They always have the same class name, only the package differs.\n"
					+ "\n"
					+ "When entity properties are changed on the client, the framework triggers the <code>ChangePropertiesServiceCall</code> immediately. This service call sends the changes to the server, which updates the server instance and sends the changes to all other active conversations/clients.\n"
					+ "\n"
					+ "On the server, on startup, all entities are loaded from XML files into memory. When entity properties are changed, the framework saves the modified entities back to the XML files.\n"
					+ "\n"
					+ "==== Model Driven Architecture ====\n"
					+ "\n"
					+ "Some source files are generated from models. These are entities and its DAOs (Data Access Objects), service calls, events and client components. The generated Java files are checked in to SCM for convinience into <code>src/generated/java/...</code>.\n"
					+ "\n"
					+ "The models are coded in Java in the <code>ScrumModelApplication</code> class or modeled in the GUI modeller <code>ScrumModeller</code> and saved in <code>model.csv</code>. Executing <code>ScrumModeller</code> and clicking <code>Save & Generate</code> generates the sources.",
			"== Kunagi Developer Guide ==\n"
					+ "\n"
					+ "Kunagi uses Git for version control. You can read up about Git on the [http://www.kernel.org/pub/software/scm/git/docs/user-manual.html Git User's Manual].\n"
					+ "\n"
					+ "=== Setting up the development environment ===\n"
					+ "\n"
					+ "Create a [http://github.com GitHub] account.\n"
					+ "\n"
					+ "On GitHub, create your personal forks of [http://github.com/witek/ilarkesto ilarkesto] and [http://github.com/witek/kunagi kunagi]. Make sure to add a public key to your Git account following the instruction on [http://help.github.com/linux-key-setup/ generating SSH keys] if you don't already have one.\n"
					+ "\n"
					+ "Install Git on your computer:\n"
					+ "\n"
					+ " sudo apt-get install git-gui\n"
					+ "\n"
					+ "Windows users can follow [http://nathanj.github.com/gitguide/tour.html this tutorial]. \n"
					+ "\n"
					+ "Set username and email in git:\n"
					+ "\n"
					+ " git config --global user.name \"Full Name\"\n"
					+ " git config --global user.email \"email@gmail.com\"\n"
					+ "\n"
					+ "Create local clones of ilarkesto and kunagi in your workspace directory:\n"
					+ "\n"
					+ " git clone git@github.com:your-github-name/ilarkesto.git\n"
					+ " git clone git@github.com:your-github-name/kunagi.git\n"
					+ "\n"
					+ "Download dependencies by executing the <code>update-libs.bsh</code> script in the ilarkesto directory.\n"
					+ "\n"
					+ "Install [http://code.google.com/webtoolkit/download.html GWT].\n"
					+ "\n"
					+ "Set the following ''Linked Resources'' in [http://eclipse.org/ Eclipse]:\n"
					+ "\n"
					+ " GWT = /path-to/gwt\n"
					+ " OUTPUT_DIR = /path-to/class-output-dir\n"
					+ "\n"
					+ "Open ilarkesto and kunagi as projects in Eclipse.\n"
					+ "\n"
					+ "Start Kunagi with Eclipse by running <code>ScrumGwtApplication.linux.launch</code> from the kunagi project.\n"
					+ "\n"
					+ "\n"
					+ "=== Collaborating ===\n"
					+ "\n"
					+ "Commit changes to your local git repository:\n"
					+ "\n"
					+ " git add .\n"
					+ " git commit -am \"my commit comment\"\n"
					+ "\n"
					+ "Push changes to your remote repository on GitHub:\n"
					+ "\n"
					+ " git push\n"
					+ "\n"
					+ "Pull changes from your remote repository on GitHub:\n"
					+ "\n"
					+ " git pull\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "=== Architecture Overview ===\n"
					+ "\n"
					+ "Kunagi is a web application, implemented in Java, based on the Google Web Toolkit (GWT). Therefore it is separated into two runtime environments, the Java server and the GWT/JavaScript client.\n"
					+ "\n"
					+ "==== GWT Client ====\n"
					+ "\n"
					+ "The client sources are Java files in <code>src/main/java/scrum/client/...</code>. These are compiled to JavaScript by GWT. The entry point is <code>ScrumGwtApplication</code>. Every single user runs his own instance of the client.\n"
					+ "\n"
					+ "==== Java Server ====\n"
					+ "\n"
					+ "The server source files are placed in <code>src/main/java/scrum/server/...</code>. The entry point is <code>ScrumWebApplication</code>.\n"
					+ "\n"
					+ "==== Client-Server communication ====\n"
					+ "\n"
					+ "The client communicates with the server through asynchronous service calls. Every service call returns the same <code>DataTransferObject</code> to the client, which contains data for the client. It can even contain data, which has nothing to do with the service call. This could be entities, which were modified by other clients.\n"
					+ "\n"
					+ "The server manages all active clients with <code>Conversation</code> objects. These conversations contain the <code>DataTransferObject</code>. New data can be put into the transfer object at any time. It gets transferred to the client with the next service call.\n"
					+ "\n"
					+ "Service calls are implemented in the <code>ScrumServiceImpl</code> class and run on the server.\n"
					+ "\n"
					+ "See '''Model Driven Architechture''' for creating service calls.\n"
					+ "\n"
					+ "==== Entities ====\n"
					+ "\n"
					+ "Entities are persistent objects. For every entity, there are two implementations. One on the server and one on the client. They always have the same class name, only the package differs.\n"
					+ "\n"
					+ "When entity properties are changed on the client, the framework triggers the <code>ChangePropertiesServiceCall</code> immediately. This service call sends the changes to the server, which updates the server instance and sends the changes to all other active conversations/clients.\n"
					+ "\n"
					+ "On the server, on startup, all entities are loaded from XML files into memory. When entity properties are changed, the framework saves the modified entities back to the XML files.\n"
					+ "\n"
					+ "==== Model Driven Architecture ====\n"
					+ "\n"
					+ "Some source files are generated from models. These are entities and its DAOs (Data Access Objects), service calls, events and client components. The generated Java files are checked in to SCM for convinience into <code>src/generated/java/...</code>.\n"
					+ "\n"
					+ "The models are coded in Java in the <code>ScrumModelApplication</code> class or modeled in the GUI modeller <code>ScrumModeller</code> and saved in <code>model.csv</code>. Executing <code>ScrumModeller</code> and clicking <code>Save & Generate</code> generates the sources.");
	}

	private static void assertDiff(String left, String right, String expectedDiff) {
		long begin = getCurrentTimeMillis();
		TokenDiff diff = new TokenDiff(left, right, new TxtDiffMarker(), new LineTokenizer(), new WordTokenizer());
		diff.diff();
		String computedDiff = combinedDiff(left, right, new TxtDiffMarker());
		long end = getCurrentTimeMillis();
		long duration = end - begin;
		if (duration > 1000) {
                        fail("Computing diff took longer than a second: " + duration + "ms.");
                }
		assertEquals(computedDiff, expectedDiff);
	}

}
