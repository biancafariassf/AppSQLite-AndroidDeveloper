package com.example.appdisney.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.example.appdisney.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisneyScreen(viewModel: DisneyViewModel) {
    var nome by remember { mutableStateOf("") }
    var ano by remember { mutableStateOf("") }
    var protagonista by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var diretor by remember { mutableStateOf("") }
    var selectedFilmeId by remember { mutableStateOf<Int?>(null) }

    val filmeList by viewModel.disneyList.collectAsState(initial = emptyList())

    var backgroundImage by remember { mutableStateOf(R.drawable.default_background) } // Imagem padrão
    var showDialog by remember { mutableStateOf(false) } // Estado para mostrar o diálogo

    val isFormValid = nome.isNotBlank() && ano.isNotBlank() && protagonista.isNotBlank() && genero.isNotBlank() && diretor.isNotBlank()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Filmes da Disney",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF673AB7),
                    fontSize = 28.sp
                )
            )

            TextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome do Filme", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = ano,
                onValueChange = { ano = it },
                label = { Text("Ano", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = diretor,
                onValueChange = { diretor = it },
                label = { Text("Diretor", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = protagonista,
                onValueChange = { protagonista = it },
                label = { Text("Protagonista", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = genero,
                onValueChange = { genero = it },
                label = { Text("Gênero", color = Color.Black) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color.White.copy(alpha = 0.9f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                )
            )

            Spacer(modifier = Modifier.height(1.dp))



            // Linha para alinhar os botões
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Botão para adicionar/atualizar filme
                Button(
                    onClick = {
                        if (isFormValid) {
                            viewModel.addOrUpdateFilme(selectedFilmeId, nome, ano.toIntOrNull() ?: 1, protagonista, genero, diretor)
                            nome = ""
                            ano = ""
                            protagonista = ""
                            genero = ""
                            diretor = ""
                            selectedFilmeId = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7), disabledContainerColor = Color(
                        0xFF673AB7
                    ).copy(alpha = 0.5f)),
                    enabled = isFormValid
                ) {
                    Text(if (selectedFilmeId == null) "Adicionar Filme" else "Atualizar Filme", color = Color.White)
                }

                // Botão para escolher tema
                Button(
                    onClick = { showDialog = true }
                ) {
                    Text("Escolher Tema", color = Color.White)
                }
            }

            // Diálogo para escolher o tema
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Escolher Tema") },
                    text = {
                        Column {
                            val backgrounds = mapOf(
                                R.drawable.background1 to "Lilo Stitch",
                                R.drawable.background2 to "Pooh",
                                R.drawable.background3 to "Moana",
                                R.drawable.background4 to "Alice no País das Maravilhas",
                                R.drawable.background5 to "Baby Yoda",
                                R.drawable.background6 to "Mickey e Minnie",
                                R.drawable.background7 to "Frozen",
                                R.drawable.background8 to "Malevola",
                                R.drawable.background9 to "Bela",
                                R.drawable.background10 to "Ariel",
                                R.drawable.background11 to "Rei Leão",
                                R.drawable.background12 to "Brana de Neve",
                                R.drawable.background13 to "Tinker Bell"
                            )
                            backgrounds.forEach { (resId, description) ->
                                Text(
                                    text = description,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            backgroundImage = resId
                                            showDialog = false
                                        }
                                        .padding(16.dp),
                                    color = Color.Black
                                )
                            }
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = { showDialog = false },
                        ) {
                            Text("Fechar")
                        }
                    },
                    containerColor = Color.White
                )
            }

            // Lista de filmes
            LazyColumn (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filmeList) { filme ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = "Nome: ${filme.nome}", color = Color.Black)
                            Text(text = "Ano: ${filme.ano}", color = Color.Black)
                            Text(text = "Diretor: ${filme.diretor}", color = Color.Black)
                            Text(text = "Protagonista: ${filme.protagonista}", color = Color.Black)
                            Text(text = "Gênero: ${filme.genero}", color = Color.Black)
                        }

                        Row {
                            // Botão de editar
                            IconButton(onClick = {
                                nome = filme.nome
                                ano = filme.ano.toString()
                                protagonista = filme.protagonista
                                genero = filme.genero
                                diretor = filme.diretor
                                selectedFilmeId = filme.id
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.Edit,
                                    modifier = Modifier.size(32.dp),
                                    contentDescription = "Editar Filme",
                                    tint = Color.Unspecified
                                )
                            }
                            // Botão de excluir
                            IconButton(onClick = { viewModel.deleteSpider(filme) }) {
                                Icon(
                                    imageVector = Icons.Outlined.Delete,
                                    modifier = Modifier.size(32.dp),
                                    contentDescription = "Excluir Filme",
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
