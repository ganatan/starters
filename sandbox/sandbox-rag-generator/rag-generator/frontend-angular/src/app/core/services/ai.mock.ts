export function reply(
  mode: string,
  data: { question?: string; style?: string; length?: string; llm?: string },
): string {
  const question = (data.question || 'Question inconnue').replace('-', ' ');
  const style = data.style || 'neutral';
  const length = data.length || 'medium';
  const llm = data.llm || 'chatgpt';
  const validMode = mode === 'rag' ? 'avec rag' : 'sans rag';

  return `Mock Frontend Angular - Demande envoyée à ${llm} en mode ${validMode}, pour la question "${question}", avec un style "${style}" et une longueur "${length}".`;
}

