//
//  TaskList.swift
//  iosApp
//
//  Created by Ankit S on 23/08/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared



struct TaskList: View {
    
    @State var modelList:[RetailTaskModel]
    
    
    var body: some View {
        NavigationView {
        
        List(modelList, id: \.self) { model in
            NavigationLink(destination: Text("Second View")) {
                RetailsTaskView(model: model)
            }
            .navigationTitle("T000\(model.id)")
          
        }
        .listStyle(PlainListStyle())
        }
        
    }
}



struct RetailsTaskView:View {
    var model :RetailTaskModel
    
    var color :Color {
         if(model.priority == Constant.shared.critical){
            return  Color.red
         }else if(model.priority == Constant.shared.urgent){
            return Color.orange
        }else{
            return  Color.blue
        }
   
    }
    
    var body: some View {

    
        HStack {
            Rectangle()
                .fill(color)
                .frame(width: 5)
            
            
            VStack(alignment: .leading){
                HStack{
                    Text(model.priority).font(.system(size: 12)).padding(2).foregroundColor(color).background(color.opacity(0.1)).cornerRadius(5)
                    
                   Text(model.name).font(.system(size: 14)).fontWeight(.semibold).lineLimit(2).frame(alignment: .leading)
                    
                }.frame(alignment:.leading).padding(4)
                
                
                Text( "Mon,Jul 12 - Sun, Jul 18 · Due: Jul 19").font(.system(size: 14)).padding(1).foregroundColor(Color.gray)
            }
            
            
        } .frame(maxWidth: .infinity,alignment: .leading)
        .cornerRadius(3)
        .overlay(
            RoundedRectangle(cornerRadius: 3)
                .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.1), lineWidth: 1)
        )
        .onTapGesture {
            
            NavigationLink(destination: Text("Second View")) {
                Text("Hello, World!")
            }
        }
        
    }
}




struct TaskList_Previews: PreviewProvider {
    static var previews: some View {
        TaskList(modelList: (RetailsTaskRepo().getToDayTaskList() as! [RetailTaskModel]))
        
    }
}



