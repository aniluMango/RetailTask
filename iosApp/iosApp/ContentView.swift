import SwiftUI
import shared

struct ContentView: View {
	let greet = Greeting().greeting()

    var body: some View {

        TabView {
            TaskList(modelList: (RetailsTaskRepo().getToDayTaskList() as! [RetailTaskModel]))
                        .tabItem {
                            Label("My Task",systemImage:"star.fill")
                        }

            TaskList(modelList: (RetailsTaskRepo().getToDayTaskList() as! [RetailTaskModel]))
                        .tabItem {
                            Label("My Store Task", systemImage: "square.and.pencil")
                        }
        
        }
    }
    
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}




struct DetailView: View {
  let discipline: String
  var body: some View {
    Text(discipline)
        .navigationBarTitle(Text(discipline), displayMode: .inline)

  }


}
