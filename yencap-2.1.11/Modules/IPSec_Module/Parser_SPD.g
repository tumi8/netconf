from SP import SP
%%
parser Parser_SPD:
	ignore: r'[\ \t]'
	token EOF:r'$'
	token EOL: r'\n'

	#__les SP utilisent certains tokens SA__

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


	#________________Les tokens SP__________
	#les tokens ont été definis selon leur ordre d'appel au cours du parsing

	#token IPV4 et IPV6 sont déjà definis plus haut
	#token PREFIXLEN:r'(\/[0-9]+)' #par exemple "/21"
	#token PORT:r'(\[[0-9]+\])|(\[any\])' #par exemple "[80]" ou [any]
	#j'ai regroupé les deux token PREFIXLEN et PORT en PREFIXLEN_PORT

	token PREFIXLEN_PORT:r'(\/[0-9]+)*((\[[0-9]+\])|(\[any\]))*'

	#token UPPER_SPEC: r'(tcp|icmp6|ip4|any)' ça ne suffit pas car tous les mots contenus dans le fichier /etc/protocols sont valables: donc il faut plutot faire comme ci-dessous:

	token UPPER_SPEC:r'[0-9a-zA-Z\-]+' #un string avec la possibilité de chiffres et d'un tiret #par exemple "tcp" ou "ipv6-crypt"

	token DIRECTION:r'(out|in|fwd)'
	token PRIORITY: r'(priority|prio)*' #Oui! ce token peut etre vide car il n'est pas obligatoire lors de la configuartion setkey.D'où le signe *
	token BASE: r'(low|def|high)*'
	token SIGNE_OFFSET: r'(\+|\-)*'
	token OFFSET:r'[0-9]*'
	token RULE_POLICY: r'(discard|none|ipsec)'
	token SPLETTERS: r'[\/\-]+' #une succession de slash et tiret utilisée sur la 3ème ligne des SP
	#token PROTOCOL:r'(esp|ah|esp-old|ah-old|ipcomp)'#déjà defini plus haut
	#token MODE: r'(transport|tunnel|any)'  #déjà defini plus haut
	token LEVEL: r'(default|use|require|unique)'
	#token RESTE_SA_SP:r'([0-9a-zA-Z\=\)\(\:\_\ \t\n]+)(?=refcnt)["refcnt"=0-9\n]+\n'  est déjà defini dans les tokens SA
	
	#________________Les rule goal et SAD____	
	rule goal:{{spd_tab=[]}} (SPD<<spd_tab>>)EOF	{{return spd_tab}}
	rule SPD<<spd_tab>>: (SP{{spd_tab.append(SP)}})*	
	rule SP:ligne1 ligne2 lignes3 reste_SA_SP 
		{{# print [ligne1[0],ligne1[1],ligne1[2],ligne2,lignes3]}} 
		{{return SP(ligne1[0],ligne1[1],ligne1[2],ligne2+" "+lignes3)}}

	#________________rule ligne1____________
		rule ligne1:src_range dst_range upperspec* EOL {{return [src_range, dst_range,upperspec]}}
			rule src_range:adresse prefixlen_port* {{return adresse+prefixlen_port}}
			rule dst_range:adresse prefixlen_port* {{return adresse+prefixlen_port}}
				rule adresse:(IPV4 	{{return IPV4}} 
							|IPV6 	{{return IPV6}}) 
							
				rule prefixlen_port:PREFIXLEN_PORT{{return PREFIXLEN_PORT}}
			rule upperspec:UPPER_SPEC {{return UPPER_SPEC}}

	#________________rule ligne2____________
		rule ligne2:direction priority base signe_offset offset rule_policy  EOL 
		{{return "-P "+direction+" "+priority+" "+base+" "+signe_offset+offset+" "+rule_policy}}
			rule direction:DIRECTION 		{{return DIRECTION}}
			rule priority:PRIORITY 			{{return PRIORITY}}
			rule base:BASE 					{{return BASE}}
			rule signe_offset:SIGNE_OFFSET 	{{return SIGNE_OFFSET}}
			rule offset:OFFSET 				{{return OFFSET}}
			rule rule_policy: RULE_POLICY	{{return RULE_POLICY}}

	#________________rule lignes3___________
	#cette rule peut etre multiligne avec le meme motif
		rule lignes3:{{les_lignes3=""}}
		{{# factorisation par (protocol sp_char1 mode sp_char2)}}
					(protocol sp_char1 mode sp_char2
					(level 
						{{une_ligne3=protocol+sp_char1+mode+sp_char2+level}}
					|(adresse_src sp_char3 adresse_dst sp_char4 level) 
						{{une_ligne3=protocol+sp_char1+mode+sp_char2+adresse_src+sp_char3+adresse_dst+sp_char4+level}}
					) EOL 
						{{les_lignes3+=" "+une_ligne3}})*  
					{{#print les_lignes3}} 
					{{return les_lignes3}}

				rule adresse_src:adresse {{return adresse}}
				rule adresse_dst:adresse {{return adresse}}
				#rule adresse:(IPV4 {{return IPV4}} |IPV6 {{return IPV6}})  cette rule est déjà definie plus haut

				rule protocol:PROTOCOL 	{{return PROTOCOL}}
				rule mode:MODE			{{return MODE}}
				rule level:LEVEL		{{return LEVEL}}

				rule sp_char1:SPLETTERS {{return SPLETTERS}}
				rule sp_char2:SPLETTERS {{return SPLETTERS}} 
				rule sp_char3:SPLETTERS {{return SPLETTERS}}
				rule sp_char4:SPLETTERS {{return SPLETTERS}}
				rule sp_char5:SPLETTERS {{return SPLETTERS}}
				#token SPLETTERS: r'[\/\-]+' #une succession de slash et tiret utilisée sur la 3ème ligne des SP. c'est déjà defini plus haut

	#________________rule reste_SA_SP_______
		rule reste_SA_SP:RESTE_SA_SP {{return RESTE_SA_SP}}
