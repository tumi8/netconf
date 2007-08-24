from SA import SA
%%
parser Parser_SAD:
	ignore: r'[\ \t]+'
	token EOF:r'$'
	token EOL: r'\n'
	
	#________________Les tokens SA__________
	#les tokens ont été definis selon leur ordre d'appel au cours du parsing

	token IPV4: r'((25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9]?[0-9]?)'
	token IPV6:		r'([0-9a-fA-F]{0,4}:){0,7}[0-9a-fA-F]{0,4}'
	token PROTOCOL:r'(esp|ah|esp-old|ah-old|ipcomp)'
	token MODE: r'(transport|tunnel|any)'

	token SPI: r'(0x[0-9a-fA-F]+)|([0-9]+)' #SPI est un hexadecimal precedé de '0x' ou bien un decimal
	token RESTE_SPI: r'([0-9a-zA-Z\=\)\(\ ]+)|(\([0-9a-zA-Z\=\)\(\ ]+)'
	#RESTE_SPI est la chaine restante apres SPI

	token ALGORITHM: r'(\bE:.*|\bA:.*|\C:.*)'

	token RESTE_SA_SP:r'([0-9a-zA-Z\=\)\(\:\_\ \t\n]+)(?=refcnt)["refcnt"=0-9\n]+\n'
	#RESTE_SA_SP est la chaine restante sur une SA ou une SP apres avoir recuperé les elements interessants, c-à-d les 3 ou 4 premieres lignes.
	#RESTE_SA_SP est formé des 3 parties suivantes:
 		#un string multiligne précédé du mot clé refcnt (assertion lookahead ?=...)
		#le motif ["refcnt" = unChiffre \n] qui peut etre ou non sur une nouvelle ligne
		#le motif \n

	#________________Les rule goal et SAD___
	rule goal:{{sad_tab=[]}} (SAD<<sad_tab>>)EOF	{{return sad_tab}}
	rule SAD<<sad_tab>>: (SA{{sad_tab.append(SA)}})*	
	rule SA:ligne1 ligne2 lignes3 reste_SA_SP 
		{{#print [ligne1[0],ligne1[1],ligne2[0],ligne2[1],ligne2[2],lignes3]}} 
		{{return SA(ligne1[0],ligne1[1],ligne2[0],ligne2[1],ligne2[2],lignes3)}} 

	#________________rule ligne1____________
		rule ligne1 :src dst EOL {{return [src,dst]}}
			rule src:adresse {{return adresse}}
			rule dst:adresse {{return adresse}}
				rule adresse:(IPV4 	{{return IPV4}} 
							|IPV6 	{{return IPV6}}) 
			
	#________________rule ligne2____________
		rule ligne2:protocol mode spi resteSPI EOL {{return [protocol, mode,spi]}} 
			rule protocol:PROTOCOL 	{{return PROTOCOL}} 
			rule mode:"mode="MODE 	{{return "-m "+MODE}}
			rule spi:"spi="SPI 		{{return SPI}}
			rule resteSPI:RESTE_SPI {{return RESTE_SPI}}		
			
	#________________rule lignes3___________
		#cette rule peut etre multiligne avec le meme motif
		rule lignes3:{{les_algorithms=""}}
					(algorithm EOL {{les_algorithms+=" "+algorithm}})* 						{{return les_algorithms}}

			rule algorithm:ALGORITHM  {{return ALGORITHM}}
			
	#________________rule reste_SA_SP_______
		rule reste_SA_SP:RESTE_SA_SP {{return RESTE_SA_SP}}
