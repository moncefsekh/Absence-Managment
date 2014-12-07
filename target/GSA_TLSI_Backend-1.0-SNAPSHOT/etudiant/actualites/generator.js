
/* Toutes les actualités */
[
    '{{repeat(5)}}',
    {
        code: '{{index()}}',
        'nom_module': '{{lorem(1,"words")}}',
        'type':'{{random("TP","TD","TD")}}',
        'code_groupe': '{{random("GPL","TQL","CAJAVA","BDA","ADC","TEC3")}}',
        titre: '{{lorem(1)}}',
        etat:'{{lorem(3)}}',
        date_ordinaire:'{{date(new Date(2014, 0, 1), new Date(2014, 11, 1), "dd-MM-YYYY")}}',
        date_changement:'{{random("","",date(new Date(2014, 0, 1), new Date(2014, 11, 1), "dd-MM-YYYY"))}}',
        present:'{{bool()}}'
       
    }
]
/*mini liste abs*/
[
    '{{repeat(4)}}',
    {
        id: '{{index()}}',
        'code_module': '{{random("GPL","TQL","CAJAVA","BDA","ADC","TEC3")}}',
        'type':'{{random("TP","TD","TD")}}',
        date_ordinaire:'{{date(new Date(2014, 0, 1), new Date(2014, 11, 1), "dd-MM")}}',
        heur_ordinaire:'{{date(new Date(2014, 0, 1), new Date(2014, 11, 1), "hh:mm")}}',
        justifiee:'{{bool()}}'
       
    }
] 