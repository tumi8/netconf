#!/usr/bin/python
%%

parser Grammar:
	
	
	token END: '$'
	token ENDOFLINE: '[\r]?\n'
	
	token SECTION: r'[a-zA-Z0-9][-a-zA-Z0-9_:.]*'
	token ATTRIBUTE: r'[a-zA-Z0-9][-a-zA-Z0-9_.]*'
	token VALUE: r'[-<>"a-zA-Z0-9.%/_\[\]*+!:@, |()${}\'=\\n#&]*'
	token COMMENTS: r'[ \t]*[;][^\n]*'
	token INCLUDE: r'#[^\n]*'
	token OTHER: r'[ \t]?[ \t]*' 
		
	rule newgoal:
					{{ lines = [] }}
		(	line		{{ lines.append(line) }}
		(
		ENDOFLINE 
		|
		END  			{{ return lines}}
		)
		)*
		
		END  	{{ return lines}}
 		

	rule goal:      line END   {{ return line }}	 
 
	rule line:
		(
		section			{{ return section }}
		|
		attribute		{{ return attribute }}
		|
		comments		{{ return comments }}
		|
		include			{{ return include }}
		|
		other			{{ return other }}
		)
		
	
	rule attribute:			{{ data = {} }}
		ATTRIBUTE		{{ data['attribute']=ATTRIBUTE }}
		'[ \t]?'
		(
		'[+]'	
		|
		''
		|
		'[ \t]*'
		)
		'='
		(
		'[ \t]?'
		|
		''
		)
		(
		'>[ \t]?'
		|
		'[ \t]?'
		)
		(
		VALUE			{{ data['value']=VALUE }}
		|
		'[ \t]?'			{{ data['value']='' }}
		)
		'[ \t]?'
		(
		comments		{{ data['info']=comments }}
		|
		other			{{ data['other']=other }}
		)
					{{ return data }}
					
	rule section:			{{ data = {} }}
		
		r'\['SECTION'\]'	{{ data['section']=SECTION }}
		'[ \t]?'
		(
		comments		{{ data['comment']=comments }}
		|
		other			{{ data['other']=other }}
		)
					{{ return data }}
					
	rule comments:			{{ data = {} }}
		
		COMMENTS		{{ data['comment']=COMMENTS[1:] }}
					{{ return data }}
	
	rule include:			{{ data = {} }}
	
		INCLUDE			{{ data['include']=INCLUDE[1:] }}
					{{ return data }}
					
	rule other:			{{ data = {} }}
		
		OTHER			{{ data['other']=OTHER }}
					{{ return data }}
	
%%
